package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.handle.lib.uv_handle_type
import io.sunglass.uv.nativelib.loop.lib._
import io.sunglass.uv.nativelib.request.lib.uv_req_t
import io.sunglass.uv.nativelib.stream.lib.uv_connect_cb
import io.sunglass.uv.nativelib.utils.lib.uv_file

import scala.scalanative.native._

package object pipe {

  @link("uv")
  @extern
  object lib {
    type uv_pipe_t = extern

    def uv_pipe_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_pipe_t], ipc: CInt): CInt = extern

    def uv_pipe_open(pipe: Ptr[uv_pipe_t], file: uv_file): CInt = extern

    def uv_pipe_bind(pipe: Ptr[uv_pipe_t], name: CString): CInt = extern

    def uv_pipe_connect(req: Ptr[uv_req_t],
                        handle: Ptr[uv_pipe_t],
                        name: CString,
                        cb: uv_connect_cb): Unit = extern

    def uv_pipe_getsockname(handle: Ptr[uv_pipe_t], name: CString, namelen: Ptr[CSize]): CInt = extern

    def uv_pipe_getpeername(handle: Ptr[uv_pipe_t], name: CString, namelen: Ptr[CSize]): CInt = extern

    def uv_pipe_pending_instances(handle: Ptr[uv_pipe_t], count: CInt): Unit = extern

    def uv_pipe_pending_count(handle: Ptr[uv_pipe_t]): CInt = extern

    def uv_pipe_pending_type(handle: Ptr[uv_pipe_t]): uv_handle_type = extern

    def uv_pipe_chmod(handle: Ptr[uv_pipe_t], flags: CInt): CInt = extern

  }

}
