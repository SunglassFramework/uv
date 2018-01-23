package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib._

import scala.scalanative.native._

package object idle {

  @link("uv")
  @extern
  object lib {
    type uv_idle_t = extern
    type uv_idle_cb = CFunctionPtr1[Ptr[uv_idle_t], Unit]

    def uv_idle_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_idle_t]): CInt = extern

    def uv_idle_start(idle: Ptr[uv_idle_t], cb: uv_idle_cb): CInt = extern

    def uv_idle_stop(idle: Ptr[uv_idle_t]): CInt = extern
  }

}
