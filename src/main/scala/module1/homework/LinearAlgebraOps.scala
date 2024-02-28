package module1.homework

object LinearAlgebraOps{
  def sum(v1: Array[Int], v2: Array[Int]): Array[Int] = {
    if (v1.length != v2.length) throw new Exception("Operation is not supported")
    else {
      val result: Array[Int] = new Array[Int](v1.length)
      for(ind <- v1.indices) result(ind) = v1(ind) + v2(ind)
      result
    }
  }

  def scale(a: Int, v: Array[Int]): Array[Int] = {
    for(ind <- v.indices) v(ind) *= 2
    v
  }

  def axpy(a: Int, v1: Array[Int], v2: Array[Int]): Array[Int] = sum(scale(a, v1), v2)
}