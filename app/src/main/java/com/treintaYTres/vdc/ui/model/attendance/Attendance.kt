package com.treintaYTres.vdc.ui.model.attendance

import com.treintaYTres.vdc.ui.model.people.Person
import com.treintaYTres.vdc.ui.model.people.StringAttendance

data class Attendance(
    val map: Map<StringAttendance, List<Person>>
)