package com.thenewj.newj.ui.main.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thenewj.newj.model.Events

class EventViewModel : ViewModel{


    var id = ""
    var title = ""
    var image = ""

    constructor(events :Events) : super() {
        this.id = events.id
        this.title = events.title
        this.image = events.image
    }

    constructor() : super()

    var arrayEventlist = MutableLiveData<ArrayList<EventViewModel>>()
    var arrayList = ArrayList<EventViewModel>()


    fun getArrayList():MutableLiveData<ArrayList<EventViewModel>>{


        val event1 = Events("1", "World Cup", "ic_cross.png")
        val event2 = Events("2", "Corona Virus", "ic_cross.png")
        val event3 = Events("3", "Olympic", "ic_plus.png")


        val eventViewModel1:EventViewModel = EventViewModel(event1)
        val eventViewModel2:EventViewModel = EventViewModel(event2)
        val eventViewModel3:EventViewModel = EventViewModel(event3)

        arrayList!!.add(eventViewModel1)
        arrayList!!.add(eventViewModel2)
        arrayList!!.add(eventViewModel3)


        arrayEventlist.value = arrayList


        return arrayEventlist
    }
}