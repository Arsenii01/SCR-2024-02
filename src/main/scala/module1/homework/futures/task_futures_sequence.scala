package module1.homework.futures

import module1.homework.futures.HomeworksUtils.TaskSyntax

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success}

object task_futures_sequence {

  /**
   * В данном задании Вам предлагается реализовать функцию fullSequence,
   * похожую на Future.sequence, но в отличии от нее,
   * возвращающую все успешные и не успешные результаты.
   * Возвращаемое тип функции - кортеж из двух списков,
   * в левом хранятся результаты успешных выполнений,
   * в правово результаты неуспешных выполнений.
   * Не допускается использование методов объекта Await и мутабельных переменных var
   */

  /**
   * @param futures список асинхронных задач
   * @return асинхронную задачу с кортежом из двух списков
   */
  def fullSequence[A](futures: List[Future[A]])
                     (implicit ex: ExecutionContext): Future[(List[A], List[Throwable])] = {
    @tailrec
    def loop(remaining: List[Future[A]],
             acc: Future[(List[A], List[Throwable])]): Future[(List[A], List[Throwable])] = {
      remaining match {
        case Nil => acc
        case head :: tail =>
          val p = Promise[(List[A], List[Throwable])]
          acc.onComplete {
            case Success((successValues, failureValues)) =>
              head.onComplete {
                case Failure(exception) =>
                  p.success((successValues, failureValues :+ exception))
                case Success(value) =>
                  p.success((successValues :+ value, failureValues))
              }
            case Failure(ex) =>
              p.failure(ex)
          }
          loop(tail, p.future)
      }
    }
    loop(futures, Future.successful(List.empty[A], List.empty[Throwable]))
  }
}
