package module1.homework

import java.lang.Integer
import scala.util.Random

object Homework3 {

  class Experiment {
    val basket:List[Int] = List(1, 1, 1, 0, 0, 0)

    def process(): Boolean = {
      var count = 0
      val ind1 = Random.nextInt(6)
      if (basket(ind1) == 1) count += 1
      val ind2 = Random.nextInt(5)
      if (basket.take(5)(ind2) == 1) count += 1
      count >= 1
    }
  }

  class Experiments(val num: Int) {
    var experiments: List[Experiment] = List()
    for (i <- 1 to num) {
       experiments = new Experiment() :: experiments
    }

    def makeExperiments(): Unit = {
      var countHadWhite: Double = 0
      experiments.foreach(e => {
        if (e.process()) countHadWhite += 1
      })
      println(countHadWhite / num)
    }
  }


  def main(args: Array[String]): Unit = {
    val experiments = new Experiments(10000)
    experiments.makeExperiments()
  }

}
