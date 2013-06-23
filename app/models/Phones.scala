package models

import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted.ColumnOption.PrimaryKey
import Database.threadLocalSession
import scala.slick.session.Database
import play.api.db.DB
import play.api.Play.current

/**
 * Little Sort Library for parsing from and to sort expressions
 * A sort expression is a sequence of column - sort order that are typically passed
 * in a URL.  An examprle would be:
 * http://localhost:9000/api/phones?sort=(age:desc;name:asc)
 */
sealed class SortDirection(val direction: String) {}

object SortDirection {

  def parse(sort: String): SortDirection = sort match {
    case s if sort.equalsIgnoreCase("desc") => Desc
    case _ => Asc
  }
}

case object Asc extends SortDirection("asc")

case object Desc extends SortDirection("desc")

object SortBy {
  def parse(sortBy: String): Seq[(String, SortDirection)] = {
    sortBy.stripPrefix("(").stripSuffix(")").split(';').map(
      semiColonDelimited => semiColonDelimited.trim()).map(
      trimmed => trimmed.split(':')).map(
      sortColOrdArr => (sortColOrdArr(0), SortDirection.parse(sortColOrdArr(1)))
    )
  }
}

/**
 * The Phone Model and persistence classes
 */
case class Phone(id: String, age: Int, imageUrl: String, name: String, snippet: String, carrier: Option[String] = None)

object Phones extends Table[Phone]("phone") {

  def id = column[String]("id", PrimaryKey)
  def age = column[Int]("age")
  def imageUrl = column[String]("image_url")
  def name = column[String]("name")
  def snippet = column[String]("snippet")
  def carrier = column[Option[String]]("carrier")
  def * = id ~ age ~ imageUrl ~ name ~ snippet ~ carrier <>(Phone, Phone.unapply _)

  lazy val database = Database.forDataSource(DB.getDataSource())

  /**
   * Pages over the phones in the system
   * @param pageNumber
   * @param pageSize
   * @param sort
   * @return A Tuple containing the sequence of phones found, and the total number of phones in the system
   */
  def page(pageNumber: Int, pageSize: Int, sort: Seq[(String, SortDirection)]): (Seq[Phone], Int) = {

    database.withSession {
      val offset = (pageNumber - 1) * pageSize

      // Here we specify the query as a var, because we are going to change it multiple times
      // start with a simple query over the phones
      var query = Query(Phones)

      // This is building the sort clause, matching on each column in the sort
      sort.foreach {
        sortTuple =>
          val (sortColumn, sortDirection) = sortTuple
          query = query.sortBy(sortColumn match {
            case "id" => if (sortDirection == Desc) _.id.desc else _.id.asc
            case "age" => if (sortDirection == Desc) _.age.desc else _.age.asc
            case "snippet" => if (sortDirection == Desc) _.snippet.desc else _.snippet.asc
            case _ => if (sortDirection == Desc) _.name.desc else _.name.asc
          })
      }

      // The "list" method actually executes the query
      val phones = query.drop(offset).take(pageSize).list

      // This finds the total count of all phones in the system
      val totalPhoneCount = Query(query.length).first

      (phones, totalPhoneCount)
    }
  }

  def findById(id: String): Option[Phone] = {
    database.withSession {
      val query = for (phone <- Phones if phone.id === id) yield phone
      query.firstOption
    }
  }

  def findAll: Seq[Phone] = {
    database.withSession {
      Query(Phones).list
    }
  }

  def deleteAll = {
    database.withSession {
      Query(Phones).delete
    }
  }
}
