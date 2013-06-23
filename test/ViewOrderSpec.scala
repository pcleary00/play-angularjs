import anorm._
import anorm._
import models._
import org.specs2.mutable._
import play.api.test._
import play.api.test._
import play.api.test.Helpers._

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 4/21/13
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
class ViewOrderSpec extends Specification {
//def is = ^
//  "View an unit that exists" ^
//    "Given an unit exists with id 12345" ^ sampleOrder ^
//    "When a REST request is made for the unit " ^ viewOrderRequest ^
//    "Then the order is returned" ^ orderResult ^
//                                 end
//
//
//  object sampleOrder extends Given[Order] {
//    def extract(text:String) = createSampleOrder()
//  }
//
//  object viewOrderRequest extends When[Order,FakeRequest] {
//    def extract(order: Order, text: String) = new FakeRequest("GET", "/orders/12345")
//  }
//
//  object orderResult extends Then[]
//
//  def createSampleOrder() = {
//    Order.create(Order(Id("12345"), "Merck", "Mckesson"))
//  }

  "GET order" should {
    "return an order given an existing order id" in {
      running(FakeApplication()) {
        // Given a valid order
        Order.create(Order(Id("12345"), "Merck", "Mckesson"))
        val result = route(FakeRequest(GET, "/orders/12345")).get

        status(result) must equalTo(OK)
        contentType(result) must beSome.which(_ == "text/json")
      }
    }
  }

}
