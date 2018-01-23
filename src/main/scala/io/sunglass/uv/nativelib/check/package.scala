package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib._

import scala.scalanative.native._

package object check {

  @link("uv")
  @extern
  object lib {
    type uv_check_t = extern
    type uv_check_cb = CFunctionPtr1[Ptr[uv_check_t], Unit]

    def uv_check_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_check_t]): CInt = extern

    def uv_check_start(check: Ptr[uv_check_t], cb: uv_check_cb): CInt = extern

    def uv_check_stop(check: Ptr[uv_check_t]): CInt = extern
  }

}
