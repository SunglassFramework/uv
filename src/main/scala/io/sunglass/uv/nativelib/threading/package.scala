package io.sunglass.uv.nativelib

import scala.scalanative.native._

package object threading {

  @link("uv")
  @extern
  object lib {
    type uv_thread_t = extern
    type uv_thread_cb = CFunctionPtr1[Ptr[Byte], Unit]
    type uv_key_t = extern
    type uv_once_t = extern
    type uv_mutex_t = extern
    type uv_rwlock_t = extern
    type uv_sem_t = extern
    type uv_cond_t = extern
    type uv_barrier_t = extern

    def uv_thread_create(tid: Ptr[uv_thread_t], entry: uv_thread_cb, arg: Ptr[Byte]): CInt = extern

    def uv_thread_self(): uv_thread_t = extern

    def uv_thread_join(tid: Ptr[uv_thread_t]): CInt = extern

    def uv_thread_equal(t1: Ptr[uv_thread_t], t2: Ptr[uv_thread_t]): CInt = extern

    def uv_key_create(key: Ptr[uv_key_t]): CInt = extern

    def uv_key_delete(key: Ptr[uv_key_t]): CInt = extern

    def uv_key_get(key: Ptr[uv_key_t]): Ptr[Byte] = extern

    def uv_key_set(key: Ptr[uv_key_t], value: Ptr[Byte]): Unit = extern

    def uv_once(guard: Ptr[uv_once_t], cb: CFunctionPtr0[Unit]): Unit = extern

    def uv_mutex_init(handle: Ptr[uv_mutex_t]): CInt = extern

    def uv_mutex_init_recursive(handle: Ptr[uv_mutex_t]): CInt = extern

    def uv_mutex_destroy(handle: Ptr[uv_mutex_t]): Unit = extern

    def uv_mutex_lock(handle: Ptr[uv_mutex_t]): Unit = extern

    def uv_mutex_trylock(handle: Ptr[uv_mutex_t]): CInt = extern

    def uv_mutex_unlock(handle: Ptr[uv_mutex_t]): Unit = extern

    def uv_rwlock_init(handle: Ptr[uv_rwlock_t]): CInt = extern


    def uv_rwlock_destroy(rwlock: Ptr[uv_rwlock_t]): Unit = extern

    def uv_rwlock_rdlock(rwlock: Ptr[uv_rwlock_t]): Unit = extern

    def uv_rwlock_tryrdlock(rwlock: Ptr[uv_rwlock_t]): CInt = extern

    def uv_rwlock_rdunlock(rwlock: Ptr[uv_rwlock_t]): Unit = extern

    def uv_rwlock_wrlock(rwlock: Ptr[uv_rwlock_t]): Unit = extern

    def uv_rwlock_trywrlock(rwlock: Ptr[uv_rwlock_t]): CInt = extern

    def uv_rwlock_wrunlock(rwlock: Ptr[uv_rwlock_t]): Unit = extern

    def uv_sem_init(sem: Ptr[uv_sem_t], value: CUnsignedInt): CInt = extern

    def uv_sem_destroy(sem: Ptr[uv_sem_t]): Unit = extern

    def uv_sem_post(sem: Ptr[uv_sem_t]): Unit = extern

    def uv_sem_wait(sem: Ptr[uv_sem_t]): Unit = extern

    def uv_sem_trywait(sem: Ptr[uv_sem_t]): CInt = extern


    def uv_cond_init(cond: Ptr[uv_cond_t]): CInt = extern

    def uv_cond_destroy(cond: Ptr[uv_cond_t]): Unit = extern

    def uv_cond_signal(cond: Ptr[uv_cond_t]): Unit = extern

    def uv_cond_broadcast(cond: Ptr[uv_cond_t]): Unit = extern

    def uv_cond_wait(cond: Ptr[uv_cond_t], mutex: Ptr[uv_mutex_t]): Unit = extern

    def uv_cond_timedwait(cond: Ptr[uv_cond_t], mutex: Ptr[uv_mutex_t], timeout: CUnsignedLongLong): CInt = extern

    def uv_barrier_init(barrier: Ptr[uv_barrier_t], count: CUnsignedInt): CInt = extern

    def uv_barrier_destroy(barrier: Ptr[uv_barrier_t]): Unit = extern


    def uv_barrier_wait(barrier: Ptr[uv_barrier_t], mutex: Ptr[uv_mutex_t]): Unit = extern

  }

}
