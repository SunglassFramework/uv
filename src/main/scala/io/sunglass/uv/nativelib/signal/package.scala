package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib.uv_loop_t

import scala.scalanative.native._

package object signal {

  @link("uv")
  @extern
  object lib {
    type uv_signal_t = extern
    type uv_signal_cb = CFunctionPtr2[Ptr[uv_signal_t], CInt, Unit]

    def uv_signal_init(loop: Ptr[uv_loop_t], signal: Ptr[uv_signal_t]): CInt = extern

    def uv_signal_start(signal: Ptr[uv_signal_t], cb: uv_signal_cb, signum: CInt): CInt = extern

    def uv_signal_start_oneshot(signal: Ptr[uv_signal_t], cb: uv_signal_cb, signum: CInt): CInt = extern

    def uv_signal_stop(signal: Ptr[uv_signal_t]): CInt = extern

  }

  @link("sunglass0")
  @extern
  object extra {
    @name("sunglass_uv_signal_size")
    def uv_signal_size(): CSize = extern
  }

}
