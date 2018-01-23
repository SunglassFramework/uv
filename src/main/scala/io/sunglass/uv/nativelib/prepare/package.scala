package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib._

import scala.scalanative.native._

package object prepare {

  @link("uv")
  @extern
  object lib {
    type uv_prepare_t = extern
    type uv_prepare_cb = CFunctionPtr1[Ptr[uv_prepare_t], Unit]

    def uv_prepare_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_prepare_t]): CInt = extern

    def uv_prepare_start(prepare: Ptr[uv_prepare_t], cb: uv_prepare_cb): CInt = extern

    def uv_prepare_stop(prepare: Ptr[uv_prepare_t]): CInt = extern
  }

}
