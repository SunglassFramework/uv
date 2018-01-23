package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.handle.lib.uv_handle_type
import io.sunglass.uv.nativelib.loop.lib.uv_loop_t
import io.sunglass.uv.nativelib.utils.lib.{uv_buf_t, uv_os_fd_t}

import scala.scalanative.native._

package object handle {

  val UV_UNKNOWN_HANDLE: uv_handle_type = 0.asInstanceOf[uv_handle_type]
  val UV_ASYNC: uv_handle_type = 1.asInstanceOf[uv_handle_type]
  val UV_CHECK: uv_handle_type = 2.asInstanceOf[uv_handle_type]
  val UV_FS_EVENT: uv_handle_type = 3.asInstanceOf[uv_handle_type]
  val UV_FS_POLL: uv_handle_type = 4.asInstanceOf[uv_handle_type]
  val UV_HANDLE: uv_handle_type = 5.asInstanceOf[uv_handle_type]
  val UV_IDLE: uv_handle_type = 6.asInstanceOf[uv_handle_type]
  val UV_NAMED_PIPE: uv_handle_type = 7.asInstanceOf[uv_handle_type]
  val UV_POLL: uv_handle_type = 8.asInstanceOf[uv_handle_type]
  val UV_PREPARE: uv_handle_type = 9.asInstanceOf[uv_handle_type]
  val UV_PROCESS: uv_handle_type = 10.asInstanceOf[uv_handle_type]
  val UV_STREAM: uv_handle_type = 11.asInstanceOf[uv_handle_type]
  val UV_TCP: uv_handle_type = 12.asInstanceOf[uv_handle_type]
  val UV_TIMER: uv_handle_type = 13.asInstanceOf[uv_handle_type]
  val UV_TTY: uv_handle_type = 14.asInstanceOf[uv_handle_type]
  val UV_UDP: uv_handle_type = 15.asInstanceOf[uv_handle_type]
  val UV_SIGNAL: uv_handle_type = 16.asInstanceOf[uv_handle_type]
  val UV_FILE: uv_handle_type = 17.asInstanceOf[uv_handle_type]
  val UV_HANDLE_TYPE_MAX: uv_handle_type = 18.asInstanceOf[uv_handle_type]

  @link("uv")
  @extern
  object lib {
    type uv_handle_t = extern
    type uv_handle_type = extern
    /**
      * Type definition for callback passed to uv_read_start() and uv_udp_recv_start().
      * The user must allocate memory and fill the supplied uv_buf_t structure.
      * If NULL is assigned as the buffer’s base or 0 as its length, a UV_ENOBUFS error will be
      * triggered in the uv_udp_recv_cb or the uv_read_cb callback.
      *
      * A suggested size (65536 at the moment in most cases) is provided, but it’s just an indication,
      * not related in any way to the pending data to be read.
      * The user is free to allocate the amount of memory they decide.
      * As an example, applications with custom allocation schemes such as using freelists, allocation pools or
      * slab based allocators may decide to use a different size which matches the memory chunks they already have.
      *
      * @example
      * {{{
      * static void my_alloc_cb(uv_handle_t* handle, size_t suggested_size, uv_buf_t* buf) {
      * buf->base = malloc(suggested_size);
      * buf->len = suggested_size;
      * }
      * }}}
      *
      */
    type uv_alloc_cb = CFunctionPtr3[Ptr[uv_handle_t], CSize, Ptr[uv_buf_t], Unit]
    type uv_close_cb = CFunctionPtr1[Ptr[uv_handle_t], Unit]

    /**
      * Returns non-zero if the handle is active, zero if it’s inactive. What “active” means depends on the type of handle:
      * *
      * A uv_async_t handle is always active and cannot be deactivated, except by closing it with uv_close().
      * A uv_pipe_t, uv_tcp_t, uv_udp_t, etc. handle - basically any handle that deals with i/o - is active when it is doing something that involves i/o, like reading, writing, connecting, accepting new connections, etc.
      * A uv_check_t, uv_idle_t, uv_timer_t, etc. handle is active when it has been started with a call to uv_check_start(), uv_idle_start(), etc.
      * *
      * Rule of thumb: if a handle of type uv_foo_t has a uv_foo_start() function, then it’s active from the moment that function is called. Likewise, uv_foo_stop() deactivates the handle again.
      *
      * @param handle
      * @return
      */
    def uv_is_active(handle: Ptr[uv_handle_t]): CInt = extern

    /**
      * Note
      * This function should only be used between the initialization of the handle and the arrival of the close callback.
      *
      * @param handle Handle
      * @return non-zero if the handle is closing or closed, zero otherwise.
      */
    def uv_is_closing(handle: Ptr[uv_handle_t]): CInt = extern

    /**
      * Request handle to be closed. close_cb will be called asynchronously after this call. This MUST be called on each handle before memory is released.
      * *
      * Handles that wrap file descriptors are closed immediately but close_cb will still be deferred to the next iteration of the event loop. It gives you a chance to free up any resources associated with the handle.
      * *
      * In-progress requests, like uv_connect_t or uv_write_t, are cancelled and have their callbacks called asynchronously with status=UV_ECANCELED.
      *
      * @param handle   Handle
      * @param close_cb close callback
      * @return
      */
    def uv_close(handle: Ptr[uv_handle_t], close_cb: uv_close_cb): CInt = extern

    /**
      * Reference the given handle. References are idempotent, that is, if a handle is already referenced calling this function again will have no effect.
      *
      * @param handle Handle
      */
    def uv_ref(handle: Ptr[uv_handle_t]): Unit = extern

    /**
      * Un-reference the given handle. References are idempotent, that is, if a handle is not referenced calling this function again will have no effect.
      *
      * @param handle Handle
      */
    def uv_unref(handle: Ptr[uv_handle_t]): Unit = extern

    /**
      * Returns non-zero if the handle referenced, zero otherwise.
      *
      * @param handle Handle
      * @return non-zero if the handle referenced, zero otherwise.
      */
    def uv_has_ref(handle: Ptr[uv_handle_t]): CInt = extern

    /**
      * Returns the size of the given handle type. Useful for FFI binding writers who don’t want to know the structure layout.
      *
      * @param handle Handle
      * @return Returns the size of the given handle type. Useful for FFI binding writers who don’t want to know the structure layout.
      */
    def uv_handle_size(handle: Ptr[uv_handle_t]): CSize = extern

    /**
      * Gets or sets the size of the send buffer that the operating system uses for the socket.
      *
      * This function works for TCP, pipe and UDP handles on Unix and for TCP and UDP handles on Windows.
      *
      * Note
      * Linux will set double the size and return double the size of the original set value.
      *
      * @param handle Handle
      * @param value  If *value == 0, it will return the current send buffer size, otherwise it will use *value to set the new send buffer size.
      * @return
      */
    def uv_send_buffer_size(handle: Ptr[uv_handle_t], value: Ptr[CInt]): CInt = extern

    /**
      * Gets or sets the size of the receive buffer that the operating system uses for the socket.
      *
      * This function works for TCP, pipe and UDP handles on Unix and for TCP and UDP handles on Windows.
      *
      * Note
      * Linux will set double the size and return double the size of the original set value.
      *
      * @param handle Handle
      * @param value  If *value == 0, it will return the current receive buffer size, otherwise it will use *value to set the new send buffer size.
      * @return
      */
    def uv_recv_buffer_size(handle: Ptr[uv_handle_t], value: Ptr[CInt]): CInt = extern

    /**
      * Gets the platform dependent file descriptor equivalent.
      * *
      * The following handles are supported: TCP, pipes, TTY, UDP and poll. Passing any other handle type will fail with UV_EINVAL.
      * *
      * If a handle doesn’t have an attached file descriptor yet or the handle itself has been closed, this function will return UV_EBADF.
      * *
      * Warning
      * *
      * Be very careful when using this function. libuv assumes it’s in control of the file descriptor so any change to it may lead to malfunction.
      *
      * @param handle
      * @param fd
      * @return
      */
    def uv_fileno(handle: Ptr[uv_handle_t], fd: Ptr[uv_os_fd_t]): CInt = extern

    def uv_handle_get_loop(handle: Ptr[uv_handle_t]): Ptr[uv_loop_t] = extern

    def uv_handle_get_data(handle: Ptr[uv_handle_t]): Ptr[Byte] = extern

    def uv_handle_set_data(handle: Ptr[uv_handle_t], data: Ptr[Byte]): Ptr[Byte] = extern

    def uv_handle_get_type(handle: Ptr[uv_handle_t]): uv_handle_type = extern

    /**
      * Returns the name for the equivalent struct for a given handle type, e.g. “pipe” (as in uv_pipe_t) for UV_NAMED_PIPE.
      * *
      * If no such handle type exists, this returns NULL.
      *
      * @param `type`
      * @return
      */
    def uv_handle_type_name(`type`: uv_handle_type): CString = extern

  }

}
