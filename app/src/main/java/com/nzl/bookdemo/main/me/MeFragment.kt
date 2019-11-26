package com.nzl.bookdemo.main.me

import android.annotation.SuppressLint
import android.util.Log
import com.nzl.bookdemo.R
import com.nzl.bookdemo.base.BaseFragment
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


/**
 * FileName:   MeFragment
 * Author:     nizonglong
 * CreateTime: 2019/11/21 18:12
 */
class MeFragment : BaseFragment() {
    override fun initLayout(): Int {
        return R.layout.fragment_me
    }

    override fun initView() {

    }

    override fun initData() {

    }

    @SuppressLint("CheckResult")
    fun testRxJava() {
        val str1 = """
            1,2,3,4,5,6
        """.trimIndent().split(",")

        val result1 = Observable.fromArray(str1)
            .subscribe {
                Log.d("rxjava fromArray", it.toString())
            }

        val list1: MutableList<Int> = ArrayList()
        list1.add(10)
        list1.add(1)
        list1.add(5)

        Flowable.just(list1)
            .flatMap<Int> {
                Flowable.fromIterable(it)
            }
            .filter {
                it > 3
            }
            .subscribe {
                Log.d("rxjava flatMap", it.toString())
            }

        val list: MutableList<String> = ArrayList()
        list.add("I")
        list.add("am")
        list.add("a")
        list.add("person")

        val result2 = Observable.fromIterable(list)
            .map { str ->
                str.hashCode()
            }
            .subscribe { num ->
                Log.d("rxjava fromIterable", num.toString())
            }


        //开始不延时，之后每隔一秒发送一次序列号从 0 开始，每次累加 1如0 1 2 3 4 5.....
        val result3 = Observable.interval(0, 1, TimeUnit.SECONDS)
            .subscribe {
                Log.d("rxjava interval", it.toString())
            }

        //3秒后会输出0
        val result4 = Observable.timer(3, TimeUnit.SECONDS)
            .subscribe {
                Log.d("rxjava timer", it.toString())
            }


        //会直接输出2 3 4 5 6
        val result5 = Observable.range(2, 5)
            .subscribe {
                Log.d("rxjava range", it.toString())
            }

        val list2: MutableList<String> = ArrayList()
        list2.add("I")
        list2.add("am")
        list2.add("a")
        list2.add("person")
        //这里使用map将重新生成一个Integer类型的Observabl最终打印出来的是每个字符串的hashCode值
        val result6 = Observable.fromIterable(list2)
            .map<Any> { str: String -> str.hashCode() }
            .subscribe {
                Log.d("rxjava map", it.toString())
            }


        Observable.fromArray(1, 2, -3, 4, 5)
            .filter { it > 3 }
            .subscribe {
                Log.d("rxjava filter", it.toString())
            }


        Observable.fromArray(1, 2, -3, 4, 5)
            .take(2)
            .subscribe {
                Log.d("rxjava take", it.toString())
            }


        Observable.fromArray(1, 2, 3, 3, 4, 5)
            .distinct()
            .subscribe {
                Log.d("rxjava distinct", it.toString())
            }

        Observable.concat(
            Observable.just(1, 2, 3),
            Observable.just(4, 5, 6)
        )
            .subscribe {
                Log.d("rxjava concat", it.toString())
            }


        Observable.just(1, 2, 3, 4, 5)
            .buffer(3, 2)
            .subscribe {
                Log.d("rxjava buffer", it.toString())
            }


        Observable.timer(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
            .subscribe {
                Log.d("rxjava timer", it.toString())
            }


        Observable.interval(3, 2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
            .subscribe {
                Log.d("rxjava interval", it.toString())
            }


        Observable.just(1, 2, 3, 4, 5)
            .skip(2)
            .subscribe {
                Log.d("rxjava skip", it.toString())
            }


        Single.just(Random().nextInt())
            .subscribe(object : SingleObserver<Int> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(integer: Int) {
                    Log.e("rxjava single", "single : onSuccess : $integer")
                }

                override fun onError(e: Throwable) {
                    Log.e("rxjava single", "single : onError : ${e.message}")
                }
            })



        Observable.create<Int> { emitter ->
            // send events with simulated time wait
            emitter.onNext(1) // skip
            Thread.sleep(400)
            emitter.onNext(2) // deliver
            Thread.sleep(505)
            emitter.onNext(3) // skip
            Thread.sleep(100)
            emitter.onNext(4) // deliver
            Thread.sleep(605)
            emitter.onNext(5) // deliver
            Thread.sleep(510)
            emitter.onComplete()
        }.debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("rxjava debounce", it.toString())
            }



        Observable.just(1, 2, 3, 4, 5)
            .last(6)
            .subscribe { it ->
                Log.d("rxjava last", it.toString())
            }


        Observable.merge(
            Observable.just(1, 2, 3),
            Observable.just(2, 3, 6)
        )
            .subscribe {
                Log.d("rxjava merge", it.toString())
            }



        Observable.just(1, 2, 3)
            .reduce { integer, integer2 ->
                integer + integer2
            }.subscribe {
                Log.e("rxjava reduce", "accept: reduce : $it")
            }


        Observable.just(1, 2, 3)
            .scan { integer, integer2 ->
                integer + integer2
            }.subscribe {
                Log.e("rxjava scan", "accept: reduce : $it")
            }


        val observer: Observer<Any> = object : Observer<Any> {
            //1
            override fun onComplete() {//2
                println("All Completed")
            }

            override fun onNext(item: Any) {//3
                println("Next $item")
            }

            override fun onError(e: Throwable) {//4
                println("Error Occured $e")
            }

            override fun onSubscribe(d: Disposable) {//5
                println("Subscribed to $d")
            }
        }

        val observable: Observable<Any> =
            listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f).toObservable() //6
        observable.subscribe(observer)//7
        val observableOnList: Observable<List<Any>> =
            Observable.just(
                listOf(
                    "One", 2, "Three", "Four",
                    4.5, "Five", 6.0f
                ),
                listOf("List with Single Item"),
                listOf(1, 2, 3, 4, 5, 6)
            )//8
        observableOnList.subscribe(observer)//9


        val observable2: Observable<String> = Observable.create<String> {
            //1
            it.onNext("Emit 1")
            it.onNext("Emit 2")
            it.onNext("Emit 3")
            it.onNext("Emit 4")
            it.onComplete()
        }
    }


}