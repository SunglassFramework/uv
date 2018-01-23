package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.loop.lib._

import scala.scalanative.native._

package object async {

  @link("uv")
  @extern
  object lib {
    type uv_async_t = extern
    type uv_async_cb = CFunctionPtr1[Ptr[uv_async_t], Unit]

    def uv_async_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_async_t], cb: uv_async_cb): CInt = extern

    def uv_async_send(async: Ptr[uv_async_t]): CInt = extern
  }

}
