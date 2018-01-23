package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.handle.lib._
import io.sunglass.uv.nativelib.utils.lib.uv_buf_t

import scala.scalanative.native._

package object stream {

  @link("uv")
  @extern
  object lib {
    type uv_stream_t = extern
    type uv_connect_t = extern
    type uv_shutdown_t = extern
    type uv_write_t = extern
    /**
      * Callback called when data was read on a stream.
      * *
      * nread is > 0 if there is data available or < 0 on error. When we’ve reached EOF, nread will be set to UV_EOF. When nread < 0,
      * the buf parameter might not point to a valid buffer; in that case buf.len and buf.base are both set to 0.
      * *
      * Note
      * *
      * nread might be 0, which does not indicate an error or EOF. This is equivalent to EAGAIN or EWOULDBLOCK under read(2).
      * *
      * The callee is responsible for stopping closing the stream when an error happens by calling [[uv_read_stop()]] or [[uv_close()]].
      * Trying to read from the stream again is undefined.
      * *
      * The callee is responsible for freeing the buffer, libuv does not reuse it. The buffer may be a null buffer (where buf->base=NULL and buf->len=0) on error.
      *
      */
    type uv_read_cb = CFunctionPtr3[Ptr[uv_stream_t], CSSize, Ptr[uv_buf_t], Unit]
    /**
      * Callback called after data was written on a stream. status will be 0 in case of success, < 0 otherwise.
      */
    type uv_write_cb = CFunctionPtr2[Ptr[uv_write_t], CInt, Unit]
    /**
      * Callback called after a connection started by [[uv_connect()]] is done. status will be 0 in case of success, < 0 otherwise.
      */
    type uv_connect_cb = CFunctionPtr2[Ptr[uv_connect_t], CInt, Unit]
    /**
      * Callback called after a shutdown request has been completed. status will be 0 in case of success, < 0 otherwise.
      */
    type uv_shutdown_cb = CFunctionPtr2[Ptr[uv_shutdown_t], CInt, Unit]
    /**
      * Callback called when a stream server has received an incoming connection.
      * The user can accept the connection by calling [[uv_accept()]]. status will be 0 in case of success, < 0 otherwise.
      */
    type uv_connection_cb = CFunctionPtr2[Ptr[uv_stream_t], CInt, Unit]

    /**
      * Shutdown the outgoing (write) side of a duplex stream.
      * It waits for pending write requests to complete.
      * The handle should refer to a initialized stream. req should be an uninitialized shutdown request struct.
      * The cb is called after shutdown is complete.
      *
      * @param req
      * @param handle
      * @param cb
      * @return < 0 return value indicates an error
      */
    def uv_shutdown(req: Ptr[uv_shutdown_t], handle: Ptr[uv_stream_t], cb: uv_shutdown_cb): CInt = extern

    /**
      * Start listening for incoming connections.
      *
      * @param stream  Stream
      * @param backlog indicates the number of connections the kernel might queue, same as listen(2).
      * @param cb      Called when a new incoming connection is received.
      * @return < 0 return value indicates an error
      */
    def uv_listen(stream: Ptr[uv_stream_t],
                  backlog: CInt,
                  cb: CFunctionPtr2[Ptr[uv_stream_t], CInt, CInt]): CInt = extern

    /**
      * This call is used in conjunction with [[uv_listen()]] to accept incoming connections.
      * Call this function after receiving a uv_connection_cb to accept the connection.
      * Before calling this function the client handle must be initialized.
      * When the [[uv_connection_cb]] callback is called it is guaranteed that this function will complete successfully the first time.
      * If you attempt to use it more than once, it may fail. It is suggested to only call this function once per uv_connection_cb call.
      * Note
      * server and client must be handles running on the same loop.
      *
      * @param server
      * @param client
      * @return < 0 return value indicates an error
      */
    def uv_accept(server: Ptr[uv_stream_t], client: Ptr[uv_stream_t]): CInt = extern

    /**
      * Read data from an incoming stream.
      * The [[uv_read_cb]] callback will be made several times until there is no more data to read or[[ uv_read_stop()]] is called.
      *
      * @param stream
      * @param alloc_cb
      * @param read_cb
      * @return
      */
    def uv_read_start(stream: Ptr[uv_stream_t], alloc_cb: uv_alloc_cb, read_cb: uv_read_cb): CInt = extern

    /**
      * Stop reading data from the stream. The [[uv_read_cb]] callback will no longer be called.
      * *
      * This function is idempotent and may be safely called on a stopped stream.
      *
      */
    def uv_read_stop(stream: Ptr[uv_stream_t]): CInt = extern

    /**
      * Write data to stream. Buffers are written in order
      *
      * @example
      * {{{
      *            void cb(uv_write_t* req, int status) {
      * /* Logic which handles the write result */
      * }
      * *
      * uv_buf_t a[] = {
      * { .base = "1", .len = 1 },
      * { .base = "2", .len = 1 }
      * };
      * *
      * uv_buf_t b[] = {
      * { .base = "3", .len = 1 },
      * { .base = "4", .len = 1 }
      * };
      * *
      * uv_write_t req1;
      * uv_write_t req2;
      * *
      * /* writes "1234" */
      * uv_write(&req1, stream, a, 2, cb);
      * uv_write(&req2, stream, b, 2, cb);
      *          }}}
      *
      * *
      * Note
      * *
      * The memory pointed to by the buffers must remain valid until the callback gets called. This also holds for [[uv_write2()]].
      * @param req
      * @param handle
      * @param bufs
      * @param nbufs
      * @param cb
      * @return
      */
    def uv_write(req: Ptr[uv_write_t], handle: Ptr[uv_stream_t], bufs: Ptr[uv_buf_t], nbufs: CUnsignedInt, cb: uv_write_cb): CInt = extern

    /**
      * Extended write function for sending handles over a pipe. The pipe must be initialized with ipc == 1.
      *
      * *
      * Note
      * *
      * send_handle must be a TCP socket or pipe, which is a server or a connection (listening or connected state). Bound sockets or pipes will be assumed to be servers.
      *
      * @param req
      * @param handle
      * @param bufs
      * @param nbufs
      * @param cb
      * @return
      */
    def uv_write2(req: Ptr[uv_write_t], handle: Ptr[uv_stream_t], bufs: Ptr[uv_buf_t], nbufs: CUnsignedInt, cb: uv_write_cb): CInt = extern

    /**
      * Same as uv_write(), but won’t queue a write request if it can’t be completed immediately.
      *
      * @param handle
      * @param bufs
      * @param nbufs
      * @return > 0: number of bytes written (can be less than the supplied buffer size).
      *         < 0: negative error code (UV_EAGAIN is returned if no data can be sent immediately).
      */
    def uv_try_write(handle: Ptr[uv_stream_t], bufs: Ptr[uv_buf_t], nbufs: CUnsignedInt): CInt = extern

    /**
      * Returns 1 if the stream is readable, 0 otherwise.
      *
      * @param handle
      * @return Returns 1 if the stream is readable, 0 otherwise.
      */
    def uv_is_readable(handle: Ptr[uv_stream_t]): CInt = extern

    /**
      * Returns 1 if the stream is writable, 0 otherwise.
      *
      * @param handle
      * @return Returns 1 if the stream is writable, 0 otherwise.
      */
    def uv_is_writable(handle: Ptr[uv_stream_t]): CInt = extern

    /**
      * Enable or disable blocking mode for a stream.
      * *
      * When blocking mode is enabled all writes complete synchronously.
      * The interface remains unchanged otherwise, e.g. completion or failure
      * of the operation will still be reported through a callback which is made asynchronously.
      * *
      * Warning
      * *
      * Relying too much on this API is not recommended. It is likely to change significantly in the future.
      * *
      * Currently only works on Windows for uv_pipe_t handles. On UNIX platforms, all uv_stream_t handles are supported.
      * *
      * Also libuv currently makes no ordering guarantee when the blocking mode is changed after write requests have already been submitted. Therefore it is recommended to set the blocking mode immediately after opening or creating the stream.
      *
      * @param handle
      * @param blocking
      * @return
      */
    def uv_stream_set_blocking(handle: Ptr[uv_stream_t], blocking: CInt): CInt = extern

    /**
      * Returns stream->write_queue_size.
      *
      * @param stream
      * @return stream->write_queue_size.
      */
    def uv_stream_get_write_queue_size(stream: Ptr[uv_stream_t]): CSize = extern

  }

}
