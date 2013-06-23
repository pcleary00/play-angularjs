import models.{Phone, Phones}
import play.api.db.DB
import play.api.GlobalSettings
import scala.slick.session.Database
import scala.slick.driver.H2Driver.simple._
import play.api.Play.current
import scala.slick.session.Database.threadLocalSession

/**
 * Created with IntelliJ IDEA.
 * User: paul
 * Date: 6/23/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
object Global extends GlobalSettings {

  override def onStart(app: play.api.Application) {

    lazy val database = Database.forDataSource(DB.getDataSource())

    database.withSession {


      // Create our phone tables (note: you need the H2Driver.simple import to get the create function here)
      try {
        Phones.ddl.create
      } catch {
        // Ignore because the tables already exist
        case e: Throwable => e.printStackTrace
      }

      Phones.deleteAll

      // Create all the phones from the tutorial
      Phones.insertAll(
        Phone("motorola-xoom-with-wi-fi", 0, "assets/app/img/phones/motorola-xoom-with-wi-fi.0.jpg", "Motorola XOOM\u2122 with Wi-Fi", "The Next, Next Generation\r\n\r\nExperience the future with Motorola XOOM with Wi-Fi, the world's first tablet powered by Android 3.0 (Honeycomb)."),
        Phone("motorola-xoom", 1, "assets/app/img/phones/motorola-xoom.0.jpg", "MOTOROLA XOOM\\u2122", "The Next, Next Generation\\n\\nExperience the future with MOTOROLA XOOM, the world's first tablet powered by Android 3.0 (Honeycomb)."),
        Phone("motorola-atrix-4g", 2, "assets/app/img/phones/motorola-atrix-4g.0.jpg", "MOTOROLA ATRIX\\u2122 4G", "MOTOROLA ATRIX 4G the world's most powerful smartphone.", Some("AT&amp;T")),
        Phone("dell-streak-7", 3, "assets/app/img/phones/dell-streak-7.0.jpg", "Dell Streak 7", "Introducing Dell\\u2122 Streak 7. Share photos, videos and movies together. It\\u2019s small enough to carry around, big enough to gather around."),
        Phone("samsung-gem", 4, "assets/app/img/phones/samsung-gem.0.jpg", "Samsung Gem\\u2122", "The Samsung Gem\\u2122 brings you everything that you would expect and more from a touch display smart phone \\u2013 more apps, more features and a more affordable price.", Some("Cellular South")),
        Phone("dell-venue", 5, "assets/app/img/phones/dell-venue.0.jpg", "Dell Venue", "The Dell Venue; Your Personal Express Lane to Everything", Some("Dell")),
        Phone("nexus-s", 6, "assets/app/img/phones/nexus-s.0.jpg", "Nexus S", "Fast just got faster with Nexus S. A pure Google experience, Nexus S is the first phone to run Gingerbread (Android 2.3), the fastest version of Android yet.", Some("Best Buy")),
        Phone("lg-axis", 7, "assets/app/img/phones/lg-axis.0.jpg", "LG Axis", "Android Powered, Google Maps Navigation, 5 Customizable Home Screens", Some("Cellular South")),
        Phone("samsung-galaxy-tab", 8, "assets/app/img/phones/samsung-galaxy-tab.0.jpg", "Samsung Galaxy Tab\\u2122", "Feel Free to Tab\\u2122. The Samsung Galaxy Tab\\u2122 brings you an ultra-mobile entertainment experience through its 7\\u201d display, high-power processor and Adobe\\u00ae Flash\\u00ae Player compatibility."),
        Phone("samsung-showcase-a-galaxy-s-phone", 9, "assets/app/img/phones/samsung-showcase-a-galaxy-s-phone.0.jpg", "Samsung Showcase\\u2122 a Galaxy S\\u2122 phone", "The Samsung Showcase\\u2122 delivers a cinema quality experience like you\\u2019ve never seen before. Its innovative 4\\u201d touch display technology provides rich picture brilliance, even outdoors", Some("Cellular South")),
        Phone("droid-2-global-by-motorola", 10, "assets/app/img/phones/droid-2-global-by-motorola.0.jpg", "DROID\\u2122 2 Global by Motorola", "The first smartphone with a 1.2 GHz processor and global capabilities.", Some("Verizon")),
        Phone("droid-pro-by-motorola", 11, "assets/app/img/phones/droid-pro-by-motorola.0.jpg", "DROID\\u2122 Pro by Motorola", "The next generation of DOES.", Some("Verizon")),
        Phone("motorola-bravo-with-motoblur", 12, "assets/app/img/phones/motorola-bravo-with-motoblur.0.jpg", "Motorola XOOM\u2122 with Wi-Fi", "The Next, Next Generation\r\n\r\nExperience the future with Motorola XOOM with Wi-Fi, the world's first tablet powered by Android 3.0 (Honeycomb)."),
        Phone("motorola-defy-with-motoblur", 13, "assets/app/img/phones/motorola-defy-with-motoblur.0.jpg", "Motorola DEFY\\u2122 with MOTOBLUR\\u2122", "Are you ready for everything life throws your way?", Some("T-Mobile")),
        Phone("t-mobile-mytouch-4g", 14, "assets/app/img/phones/t-mobile-mytouch-4g.0.jpg", "T-Mobile myTouch 4G", "The T-Mobile myTouch 4G is a premium smartphone designed to deliver blazing fast 4G speeds so that you can video chat from practically anywhere, with or without Wi-Fi.", Some("T-Mobile")),
        Phone("samsung-mesmerize-a-galaxy-s-phone", 15, "assets/app/img/phones/samsung-mesmerize-a-galaxy-s-phone.0.jpg", "Samsung Mesmerize\\u2122 a Galaxy S\\u2122 phone", "The Samsung Mesmerize\\u2122 delivers a cinema quality experience like you\\u2019ve never seen before. Its innovative 4\\u201d touch display technology provides rich picture brilliance,even outdoors", Some("US Cellular")),
        Phone("sanyo-zio", 16, "assets/app/img/phones/sanyo-zio.0.jpg", "SANYO ZIO", "The Sanyo Zio by Kyocera is an Android smartphone with a combination of ultra-sleek styling, strong performance and unprecedented value.", Some("Sprint")),
        Phone("samsung-transform", 17, "assets/app/img/phones/t-mobile-g2.0.jpg", "Samsung Transform\\u2122", "The Samsung Transform\\u2122 brings you a fun way to customize your Android powered touch screen phone to just the way you like it through your favorite themed \\u201cSprint ID Service Pack\\u201d."),
        Phone("t-mobile-g2", 18, "assets/app/img/phones/t-mobile-g2.0.jpg", "T-Mobile G2", "The T-Mobile G2 with Google is the first smartphone built for 4G speeds on T-Mobile's new network. Get the information you need, faster than you ever thought possible.", Some("T-Mobile")),
        Phone("motorola-charm-with-motoblur", 19, "assets/app/img/phones/motorola-charm-with-motoblur.0.jpg", "Motorola CHARM\\u2122 with MOTOBLUR\\u2122", "Motorola CHARM fits easily in your pocket or palm. Includes MOTOBLUR service.")
      )
    }
  }
}
