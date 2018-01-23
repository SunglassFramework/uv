package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.fsevent.lib.{uv_fs_event, uv_fs_event_flag}
import io.sunglass.uv.nativelib.loop.lib._

import scala.scalanative.native._

package object fsevent {
  val UV_RENAME: uv_fs_event = 1.asInstanceOf[uv_fs_event]
  val UV_CHANGE: uv_fs_event = 2.asInstanceOf[uv_fs_event]

  val UV_FS_EVENT_WATCH_ENTRY: uv_fs_event_flag = 1.asInstanceOf[uv_fs_event_flag]
  val UV_FS_EVENT_STAT: uv_fs_event_flag = 2.asInstanceOf[uv_fs_event_flag]
  val UV_FS_EVENT_RECURSIVE: uv_fs_event_flag = 4.asInstanceOf[uv_fs_event_flag]

  @link("uv")
  @extern
  object lib {
    type uv_fs_event_t = extern
    type uv_fs_event = extern
    type uv_fs_event_flag = extern
    type uv_fs_event_cb = CFunctionPtr3[Ptr[uv_fs_event_t], CString, CInt, Unit]

    def uv_fs_event_init(loop: Ptr[uv_loop_t], handle: Ptr[uv_fs_event_t]): CInt = extern

    def uv_fs_event_start(handle: Ptr[uv_fs_event_t], cb: uv_fs_event_cb, path: CString, flags: uv_fs_event_flag): CInt = extern

    def uv_fs_event_stop(handle: Ptr[uv_fs_event_t]): CInt = extern

    def uv_fs_event_getpath(handle: Ptr[uv_fs_event_t], buffer: CString, size: Ptr[CSize]): CInt = extern
  }

}
