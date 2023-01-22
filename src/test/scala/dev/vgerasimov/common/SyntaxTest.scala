package dev.vgerasimov.common

import org.scalacheck.Prop.*

class SyntaxTest extends munit.ScalaCheckSuite:
  import syntax.*
  import syntax.either.*

  test("asRight should wrap a String into Either.Right") {
    forAll { (s: String) => assertEquals(s.asRight, Right(s)) }
  }

  test("asLeft should wrap a String into Either.Left") {
    forAll { (s: String) => assertEquals(s.asLeft, Left(s)) }
  }

  test("unwrap unraps Left string value") {
    forAll { (s: String) => assertEquals(Left(s).unwrap, s) }
  }

  test("unwrap unraps Right string value") {
    forAll { (s: String) => assertEquals(Right(s).unwrap, s) }
  }

  test("partitionToEither correctly partitions empty list") {
    assertEquals(Nil.partitionToEither, Right(Nil))
  }

  test("partitionToEither correctly partitions list with no lefts") {
    assertEquals(
      List(Right(1), Right(2), Right(3)).partitionToEither,
      Right(List(1, 2, 3))
    )
  }

  test("partitionToEither correctly partitions list with lefts") {
    assertEquals(
      List(Right(1), Left(2), Right(3)).partitionToEither,
      Left(List(2))
    )
  }
