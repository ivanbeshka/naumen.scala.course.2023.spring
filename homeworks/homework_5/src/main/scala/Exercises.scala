
object Exercises {
  trait Animal {
    def name: String
  }

  case class Cat(override val name: String) extends Animal

  case class Dog(override val name: String) extends Animal

  case class Shelter[+A <: Animal](list: List[A]) {

    def +[T >: A <: Animal](other: T): Shelter[T] = {
      Shelter(list :+ other)
    }

    def ++[T >: A <: Animal](other: Shelter[T]): Shelter[T] = {
      Shelter(list ++ other.list)
    }

    def getNames(): List[String] = {
      list.map(_.name)
    }

    def feed(food: Food[A]): List[String] = {
      list.map(food.feed)
    }
  }


  trait Food[-T <: Animal] {

    val name: String

    def feed(animal: T): String = {
      s"${animal.name} eats $name"
    }
  }

  case object Meat extends Food[Animal] {
    override val name: String = "meat"
  }

  case object Milk extends Food[Cat] {
    override val name: String = "milk"
  }

  case object Bread extends Food[Dog] {
    override val name: String = "bread"
  }
}
