package com.treintaYTres.vdc.ui.mapper

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.network.model.request.CreateEventRequest
import com.treintaYTres.vdc.network.model.request.RollCallRequest
import com.treintaYTres.vdc.network.model.response.band.BandResponse
import com.treintaYTres.vdc.network.model.response.band.NewResponse
import com.treintaYTres.vdc.network.model.response.event.EventResponse
import com.treintaYTres.vdc.network.model.response.event.EventResponseItem
import com.treintaYTres.vdc.network.model.response.eventdetails.DetailResponse
import com.treintaYTres.vdc.network.model.response.eventdetails.DetailedEventResponse
import com.treintaYTres.vdc.network.model.response.eventdetails.EventDetailsResponse
import com.treintaYTres.vdc.network.model.response.eventdetails.InstrumentStringResponse
import com.treintaYTres.vdc.network.model.response.instrument.InstrumentResponse
import com.treintaYTres.vdc.network.model.response.instrument.InstrumentsResponse
import com.treintaYTres.vdc.network.model.response.member.MemberResponse
import com.treintaYTres.vdc.network.model.response.profile.ProfileResponse
import com.treintaYTres.vdc.network.model.response.profile.StatResponse
import com.treintaYTres.vdc.network.model.response.rollcall.RollCallItemResponse
import com.treintaYTres.vdc.network.model.response.rollcall.RollCallResponse
import com.treintaYTres.vdc.ui.model.Chip
import com.treintaYTres.vdc.ui.model.Header
import com.treintaYTres.vdc.ui.model.Icon
import com.treintaYTres.vdc.ui.model.InstrumentChip
import com.treintaYTres.vdc.ui.model.New
import com.treintaYTres.vdc.ui.model.band.BandInfo
import com.treintaYTres.vdc.ui.model.create.EventToCreate
import com.treintaYTres.vdc.ui.model.create.Group
import com.treintaYTres.vdc.ui.model.event.Detail
import com.treintaYTres.vdc.ui.model.event.DetailedEvent
import com.treintaYTres.vdc.ui.model.event.Event
import com.treintaYTres.vdc.ui.model.event.EventDetails
import com.treintaYTres.vdc.ui.model.event.InstrumentString
import com.treintaYTres.vdc.ui.model.event.Member
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.model.profile.Profile
import com.treintaYTres.vdc.ui.model.profile.Stat
import com.treintaYTres.vdc.ui.model.profile.Type
import com.treintaYTres.vdc.ui.model.rollcall.RollCallItem
import com.treintaYTres.vdc.ui.util.Constant
import com.treintaYTres.vdc.util.toInstrumentUrl
import com.treintaYTres.vdc.util.toProfileUrl
import com.treintaYTres.vdc.util.toStringUrl

fun EventResponse.toEvents() = this.map { it.toEvent() }


fun EventResponseItem.toEvent(): Event {
    val confirmation = when (confirmationState) {
        true -> Constant.Event.POSITIVE_CONFIRMATION
        false -> Constant.Event.NEGATIVE_CONFIRMATION
        null -> Constant.Event.PENDING_CONFIRMATION
    }
    return Event(
        mutableStateOf(confirmation),
        date,
        id,
        location,
        title,
        type
    )
}

fun EventDetailsResponse.toEventDetails() =
    EventDetails(
        details.toDetails(),
        event.toDetailedEvent()
    )

fun DetailResponse.toDetail() =
    Detail(
        instrumentString.toInstrumentString(),
        members.toMembers()
    )

fun List<DetailResponse>.toDetails() =
    map {
        it.toDetail()
    }

fun DetailedEventResponse.toDetailedEvent() =
    DetailedEvent(
        comments,
        confirmed,
        date,
        id,
        location,
        rollCallRealized,
        title,
        type
    )

fun (Boolean?).toInt() = when (this) {
    true -> Constant.Event.POSITIVE_CONFIRMATION
    false -> Constant.Event.NEGATIVE_CONFIRMATION
    null -> Constant.Event.PENDING_CONFIRMATION
}

fun MemberResponse.toMember() =
    Member(
        attendance.toInt(),
        id,
        mutableStateOf(instruments.toInstruments()),
        mutableStateOf(isAdmin),
        name,
        perteneceJunta,
        url?.toProfileUrl() ?: ""
    )

fun List<MemberResponse>.toMembers() =
    this.map {
        it.toMember()
    }

fun InstrumentStringResponse.toInstrumentString() =
    InstrumentString(
        confirmed,
        cancelled,
        id,
        name,
        url?.toStringUrl() ?: ""
    )

fun InstrumentResponse.toInstrument() =
    Instrument(
        id,
        name,
        url.toInstrumentUrl()
    )

fun List<InstrumentResponse>.toInstruments() =
    map {
        it.toInstrument()
    }

fun NewResponse.toNew() =
    New(
        text,
        Header(
            title,
            Icon.DrawableIcon(R.drawable.banda_avatar)
        ),
        date
    )

fun List<NewResponse>.toNews() = map {
    it.toNew()
}

fun BandResponse.toBandInfo() =
    BandInfo(
        isAdmin,
        members.toMembers().toMutableStateList(),
        news.toNews()
    )

fun InstrumentsResponse.toInstruments() =
    instruments.map {
        it.toInstrument()
    }

fun ProfileResponse.toProfile() =
    Profile(
        name,
        instruments.toInstruments(),
        stats.toStats(),
        url.toProfileUrl()
    )

fun StatResponse.toStat() =
    Stat(
        assistance,
        total,
        type.toEventType()
    )

fun List<StatResponse>.toStats() = map {
    it.toStat()
}

fun Int.toEventType() = when (this) {
    0 -> Constant.EventType.REHEARSAL
    else -> Constant.EventType.PERFORMANCE
}

fun List<Instrument>.toChips() = map {
    it.toChip()
}

fun Instrument.toChip() = InstrumentChip(
    name,
    url
) {}

fun Type.toChip() = Chip(
    name,
    (icon as Icon.DrawableIcon).resource
) {

}


fun List<Type>.toTypeChips(onClick: (String) -> Unit) =
    filter { it.icon is Icon.DrawableIcon }.map {
        it.toChip().apply {
            this.onClick = {
                onClick(this.title)
            }
        }
    }

fun com.treintaYTres.vdc.network.model.response.group.Group.toGroup() =
    Group(id, name, url.toStringUrl())

fun List<com.treintaYTres.vdc.network.model.response.group.Group>.toGroups() = map {
    it.toGroup()
}

fun EventToCreate.toCreateEventRequest() = CreateEventRequest(
    type.value,
    title.value,
    comments.value,
    location.value,
    date.value,
    time.value,
    instrumentStringIds
)

fun RollCallItemResponse.toRollCallItem() = RollCallItem(
    mutableStateOf(attendance),
    id,
    instruments[0].toInstrument(),
    name,
    url.toProfileUrl()
)

fun RollCallResponse.toRollCall() = map {
    it.toRollCallItem()
}

fun List<RollCallItem>.toRollCallRequest() = map {
    it.toRollCallRequest()
}

fun RollCallItem.toRollCallRequest() = RollCallRequest(
    id,
    attendance.value
)