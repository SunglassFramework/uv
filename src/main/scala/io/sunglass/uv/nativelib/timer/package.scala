package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib.uv_loop_t

import scala.scalanative.native._

package object timer {

  @link("uv")
  @extern
  object lib {
    type uv_timer_t = extern
    type uv_timer_cb = CFunctionPtr1[Ptr[uv_timer_t], Unit]

    def uv_timer_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_timer_t]): CInt = extern

    def uv_timer_start(handle: Ptr[uv_timer_t], cb: uv_timer_cb, timeout: CUnsignedLongLong, repeat: CUnsignedLongLong): CInt = extern

    def uv_timer_stop(handle: Ptr[uv_timer_t]): CInt = extern

    def uv_timer_again(handle: Ptr[uv_timer_t]): CInt = extern

    def uv_timer_set_repeat(handle: Ptr[uv_timer_t], repeat: CUnsignedLongLong): Unit = extern

    def uv_timer_get_repeat(handle: Ptr[uv_timer_t]): CUnsignedLongLong = extern
  }

}
