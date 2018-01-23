package io.sunglass.uv.nativelib

import io.sunglass.uv.nativelib.handle.lib.uv_handle_t
import io.sunglass.uv.nativelib.loop.lib.uv_run_mode

import scala.scalanative.native._

package object loop {
  val UV_RUN_DEFAULT: uv_run_mode = 0.asInstanceOf[uv_run_mode]
  val UV_RUN_ONCE: uv_run_mode = 1.asInstanceOf[uv_run_mode]
  val UV_RUN_NOWAIT: uv_run_mode = 2.asInstanceOf[uv_run_mode]

  @link("uv")
  @extern
  object lib {

    type uv_loop_option = extern
    type uv_run_mode = extern
    type uv_loop_t = extern
    type uv_walk_cb = CFunctionPtr2[Ptr[uv_handle_t], Ptr[Byte], Unit]

    /** Initializes the given [[uv_loop_t]] structure.
      *
      * @see [[http://docs.libuv.org/en/v1.x/loop.html#c.uv_loop_init]]
      */
    def uv_loop_init(loop: Ptr[uv_loop_t]): CInt = extern

    def uv_loop_close(loop: Ptr[uv_loop_t]): CInt = extern

    /** Returns the initialized default loop. It may return NULL in case of allocation failure.
      * This function is just a convenient way for having a global loop throughout an application,
      * the default loop is in no way different than the ones initialized with [[uv_loop_init()]].
      * As such, the default loop can (and should) be closed with [[uv_loop_close()]] so the resources associated with it are freed.
      *
      * @see [[http://docs.libuv.org/en/v1.x/loop.html#c.uv_default_loop]]
      */
    def uv_default_loop(): Ptr[uv_loop_t] = extern

    def uv_run(loop: Ptr[uv_loop_t], mode: uv_run_mode): CInt = extern

    /**
      * Returns non-zero if there are referenced active handles, active requests or closing handles in the loop.
      *
      * @param loop
      * @return
      */
    def uv_loop_alive(loop: Ptr[uv_loop_t]): CInt = extern

    /**
      * Stop the event loop, causing [[uv_run()]] to end as soon as possible.
      * This will happen not sooner than the next loop iteration.
      * If this function was called before blocking for i/o, the loop won’t block for i/o on this iteration.
      *
      * @param loop
      * @return
      */
    def uv_stop(loop: Ptr[uv_loop_t]): CInt = extern

    /**
      * Returns the size of the uv_loop_t structure.
      * Useful for FFI binding writers who don’t want to know the structure layout.
      *
      * @return
      */
    def uv_loop_size(): CSize = extern

    /**
      * Get backend file descriptor. Only kqueue, epoll and event ports are supported.
      * *
      * This can be used in conjunction with uv_run(loop, UV_RUN_NOWAIT) to poll in one thread and run the event loop’s callbacks in another see test/test-embed.c for an example.
      * *
      * Note
      * *
      * Embedding a kqueue fd in another kqueue pollset doesn’t work on all platforms. It’s not an error to add the fd but it never generates events.
      *
      * @param loop
      * @return
      */
    def uv_backend_fd(loop: Ptr[uv_loop_t]): CInt = extern

    /**
      * Return the current timestamp in milliseconds.
      * The timestamp is cached at the start of the event loop tick, see [[uv_update_time()]] for details and rationale.
      * *
      * The timestamp increases monotonically from some arbitrary point in time. Don’t make assumptions about the starting point, you will only get disappointed.
      * *
      * Note
      * *
      * Use [[uv_hrtime()]] if you need sub-millisecond granularity.
      *
      * @param loop
      * @return
      */
    def uv_now(loop: Ptr[uv_loop_t]): CUnsignedLongLong = extern

    /**
      * Update the event loop’s concept of “now”.
      * Libuv caches the current time at the start of the event loop tick in order to reduce the number of time-related system calls.
      * *
      * You won’t normally need to call this function unless you have callbacks that block the event
      * loop for longer periods of time, where “longer” is somewhat subjective but probably on the order of a millisecond or more.
      *
      * @param loop
      */
    def uv_update_time(loop: Ptr[uv_loop_t]): Unit = extern

    /**
      * Walk the list of handles: walk_cb will be executed with the given arg.
      */
    def uv_walk(loop: Ptr[uv_loop_t], walk_cb: uv_walk_cb, arg: Ptr[Byte]): Unit = extern

    /**
      * Reinitialize any kernel state necessary in the child process after a fork(2) system call.
      * *
      * Previously started watchers will continue to be started in the child process.
      * *
      * It is necessary to explicitly call this function on every event loop created in the parent process that you plan to continue to use in the child, including the default loop (even if you don’t continue to use it in the parent). This function must be called before calling uv_run() or any other API function using the loop in the child. Failure to do so will result in undefined behaviour, possibly including duplicate events delivered to both parent and child or aborting the child process.
      * *
      * When possible, it is preferred to create a new loop in the child process instead of reusing a loop created in the parent. New loops created in the child process after the fork should not use this function.
      * *
      * This function is not implemented on Windows, where it returns UV_ENOSYS.
      * *
      * Caution
      * *
      * This function is experimental. It may contain bugs, and is subject to change or removal. API and ABI stability is not guaranteed.
      * *
      * Note
      * *
      * On Mac OS X, if directory FS event handles were in use in the parent process for any event loop, the child process will no longer be able to use the most efficient FSEvent implementation. Instead, uses of directory FS event handles in the child will fall back to the same implementation used for files and on other kqueue-based systems.
      * *
      * Caution
      * *
      * On AIX and SunOS, FS event handles that were already started in the parent process at the time of forking will not deliver events in the child process; they must be closed and restarted. On all other platforms, they will continue to work normally without any further intervention.
      * *
      * Caution
      * *
      * Any previous value returned from :c:func`uv_backend_fd` is now invalid. That function must be called again to determine the correct backend file descriptor.
      *
      * @param loop
      * @return
      */
    def uv_loop_fork(loop: Ptr[uv_loop_t]): CInt = extern

    def uv_loop_get_data(loop: Ptr[uv_loop_t]): Ptr[Byte] = extern

    def uv_loop_set_data(loop: Ptr[uv_loop_t], data: Ptr[Byte]): Ptr[Byte] = extern
  }

}
