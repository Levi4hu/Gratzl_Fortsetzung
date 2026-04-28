package com.example.gratzl.data.model

object SampleData {

    val users = listOf(
        UserProfile(
            id = 1,
            name = "Anna M.",
            district = "Favoriten",
            bio = "Studentin, hilft gerne bei Nachhilfe und Gartenarbeit.",
            rating = 4.8f,
            skills = "Nachhilfe, Gartenarbeit, Hundesitter"
        ),
        UserProfile(
            id = 2,
            name = "Ben K.",
            district = "Mariahilf",
            bio = "Handwerker mit Erfahrung.",
            rating = 4.5f,
            skills = "Reparaturen, Umzug, Malerarbeiten"
        ),
        UserProfile(
            id = 3,
            name = "Kali K.",
            district = "Donaustadt",
            bio = "Junger Familienvater mit Erfahrung mit Rohren.",
            rating = 4.2f,
            skills = "Reparaturen, Rohrverlegung"
        ),
        UserProfile(
            id = 4,
            name = "Luca C.",
            district = "Leopoldstadt",
            bio = "Infomratik Student spezifiziert auf Kotlin",
            rating = 5.0f,
            skills = "Programmierung, Kotlin, Java"
        )
    )

    var listings = mutableListOf<Listing>(
        Listing(
            id = 1,
            title = "Nachhilfestunden Mathematik",
            description = "Biete Nachhilfe für Schüler bis Klasse 10.",
            isOffer = true,
            category = "Bildung",
            district = "Favoriten",
            priceType = PriceType.PER_HOUR,
            price = 15.0,
            urgency = UrgencyTag.FLEXIBLE,
            userId = 1
        ),
        Listing(
            id = 2,
            title = "Suche Umzugshilfe",
            description = "Brauche 2 Personen für einen Samstag.",
            isOffer = false,
            category = "Haushalt",
            district = "Mariahilf",
            priceType = PriceType.TRADE,
            urgency = UrgencyTag.TODAY,
            userId = 2
        ),
    )

    val chats = listOf(
        Chat(
            id = 1,
            listingId = 1,
            partnerId = 2,
            messages = listOf(
                ChatMessage(1, 1, 2, "Hallo, ist der Platz noch frei?", "14:30"),
                ChatMessage(2, 1, 1, "Ja! Wann passt dir?", "14:32"),
                ChatMessage(3, 1, 2, "Freitag um 17 Uhr?", "14:33"),
            )
        ),
    )

    fun getUserById(id: Int) = users.find { it.id == id }
    fun getListingById(id: Int) = listings.find { it.id == id }
    fun getChatById(id: Int) = chats.find { it.id == id }
    fun getChatForListing(listingId: Int) = chats.find { it.listingId == listingId }
}