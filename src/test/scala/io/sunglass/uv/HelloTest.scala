package io.sunglass.uv

import io.sunglass.uv.nativelib.loop._
import io.sunglass.uv.nativelib.loop.lib._
import io.sunglass.uv.nativelib.signal.extra._
import io.sunglass.uv.nativelib.signal.lib._
import io.sunglass.uv.nativelib.system.lib._
import utest._

import scala.scalanative.native._
import scala.scalanative.native.stdio.printf
import scala.scalanative.native.stdlib._

object HelloTest extends TestSuite {
  val tests = Tests {
    'uv - {
      printf(c"%d %d", SIGUSR1, uv_signal_size())
      val loop = stackalloc[uv_loop_t](uv_loop_size())

      var r = uv_loop_init(loop)

      r = uv_run(loop, UV_RUN_DEFAULT)
      assert(r == 0)

      uv_loop_close(loop)

    }
  }

  def cb(ptr: Ptr[uv_signal_t], signal: CInt): Unit = {
    printf(c"Signal %d", signal)
  }
}
