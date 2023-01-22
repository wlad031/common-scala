package dev.vgerasimov.common
package syntax

/** Contains common extensions for all types. */
extension [A](a: A)

  /** Wraps value into [[Right]] instance of [[Either]]. */
  def asRight: Right[Nothing, A] = Right(a)

  /** Wraps value into [[Left]] instance of [[Either]]. */
  def asLeft: Left[A, Nothing] = Left(a)

/** Contains [[Either]]-related extensions. */
object either:

  /** Contains extensions for [[Either]] instances. */
  extension [A](either: Either[A, A])
    /** Returns actual value of [[Either]], whether is is [[Left]] or [[Right]]. */
    def unwrap: A = either match
      case Left(a)  => a
      case Right(a) => a

  /** Contains extensions for a list of [[Either]]s. */
  extension [A, B](listEithers: List[Either[A, B]])
    /** Partitions a list of [[Either]]s into either of lists.
      *
      * If there is at least of [[Left]] in the original list, this function returns a [[Left]] with
      * all unwrapped left values. If there is no [[Left]]s in the original list, returns [[Right]]
      * with all unwrapped right values.
      */
    def partitionToEither: Either[List[A], List[B]] = listEithers.partition(_.isLeft) match
      case (Nil, rights) => Right(rights.map(_.asInstanceOf[Right[?, B]].value))
      case (lefts, _)    => Left(lefts.map(_.asInstanceOf[Left[A, ?]].value))
