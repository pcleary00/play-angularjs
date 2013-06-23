package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.{Desc, SortBy, Phones, Phone}

object Application extends Controller {

  implicit val phoneReads = Json.reads[Phone]
  implicit val phoneWrites = Json.writes[Phone]

  implicit val pageWrites = (
    (__ \ 'rows).write[Seq[Phone]] and
      (__ \ 'count).write[Int]
    ).tupled : Writes[(Seq[Phone], Int)]

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def angular = Action {
    Ok(views.html.angular())
  }

  def phone(id: String) = Action {
    val phone = Phones.findById(id)
    phone match {
      case Some(p) => Ok(Json.toJson(p))
      case None => NotFound
    }
  }

  def phones = Action {
    val results = Phones.findAll
    Ok(Json.stringify(Json.toJson(results)))
  }

  def page = Action { implicit request =>
    val pageNumber = request.queryString.get("pageNumber").getOrElse(Seq("1"))(0).toInt
    val pageSize = request.queryString.get("pageSize").getOrElse(Seq("10"))(0).toInt
    val sort = request.queryString.get("sort") match {
      case Some(s) => SortBy.parse(s(0))
      case _ => Seq(("name", Desc))
    }

    println("\r\n\r\n!!!Paging " + pageNumber + "; " + pageSize + "; " + sort)

    val results = Phones.page(pageNumber, pageSize, sort)
    Ok(Json.toJson(results))
  }
  
}