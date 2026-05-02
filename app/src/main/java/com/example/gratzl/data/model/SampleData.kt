package com.example.gratzl.data.model

object SampleData {

    val users = listOf(
        UserProfile(
            id = 1,
            name = "Sophie Huber",
            district = "Wien Leopoldstadt",
            bio = "Hilfsbereite Nachbarin aus dem 2. Bezirk. Ich biete Unterstützung beim Einkaufen, Pflanzengießen und kleine Reparaturen im Haushalt an.",
            rating = 4.9f,
            reviewCount = 12,
            responseTime = "2h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Gartenhilfe", "Gießen & Pflege von Zimmer- und Balkonpflanzen."),
                UserSkill("Einkäufe", "Wöchentliche Besorgungen und schwere Taschen."),
                UserSkill("Reparaturen", "Kleinere handwerkliche Hilfe im Haushalt."),
                UserSkill("Pet-Sitting", "Hunde ausführen oder Katzen füttern.")
            )
        ),
        UserProfile(
            id = 2,
            name = "Markus Weber",
            district = "Wien Leopoldstadt",
            bio = "Ich helfe gerne meinen Nachbarn im 2. Bezirk bei alltäglichen Aufgaben. Zuverlässigkeit und ein freundlicher Umgang sind mir wichtig.",
            rating = 4.9f,
            reviewCount = 12,
            responseTime = "< 1h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Gartenhilfe"),
                UserSkill("Einkäufe"),
                UserSkill("Reparaturen"),
                UserSkill("Pet-Sitting")
            )
        ),
        UserProfile(
            id = 3,
            name = "Kali K.",
            district = "Donaustadt",
            bio = "Junger Familienvater mit Erfahrung mit Rohren.",
            rating = 4.2f,
            reviewCount = 5,
            skills = listOf(
                UserSkill("Reparaturen", "Kleine Reparaturen im Haushalt."),
                UserSkill("Rohrverlegung")
            )
        ),
        UserProfile(
            id = 4,
            name = "Luca C.",
            district = "Leopoldstadt",
            bio = "Informatik Student mit Erfahrung in Kotlin.",
            rating = 5.0f,
            reviewCount = 8,
            skills = listOf(
                UserSkill("Programmierung"),
                UserSkill("Kotlin"),
                UserSkill("Java")
            )
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
        Listing(
            id = 3,
            title = "Biete Rohrreperatur",
            description = "Schnelle Hilfe bei defekten Rohren.",
            isOffer = true,
            category = "Handwerk",
            district = "Donaustadt",
            priceType = PriceType.FIXED,
            price = 100.0,
            urgency = UrgencyTag.FLEXIBLE,
            userId = 3
        ),
        Listing(
            id = 4,
            title = "Biete Programmierhilfe",
            description = "Durch mein Uni Erfahrung in Programmieren kann ich einiges.",
            isOffer = true,
            category = "Bildung",
            district = "Leopoldstadt",
            priceType = PriceType.COFFEE,
            urgency = UrgencyTag.FLEXIBLE,
            userId = 4
        )
    )

    val chats = mutableListOf(
        Chat(
            id = 1,
            listingId = 1,
            partnerId = 2,
            messages = listOf(
                ChatMessage(1, 1, 2, "Hallo Anna, ist ein Nachhilfeplatz noch frei?", "14:30"),
                ChatMessage(2, 1, 1, "Ja, diese Woche habe ich noch Zeit.", "14:32"),
                ChatMessage(3, 1, 2, "Perfekt. Passt Donnerstag um 17 Uhr?", "14:35"),
                ChatMessage(4, 1, 1, "Donnerstag passt gut. Ich bereite Aufgaben vor.", "14:38"),
            )
        ),
        Chat(
            id = 2,
            listingId = 2,
            partnerId = 2,
            messages = listOf(
                ChatMessage(5, 2, 1, "Hallo Ben, ich kann dir am Samstag beim Umzug helfen.", "Gestern"),
                ChatMessage(6, 2, 2, "Super, wir starten um 9 Uhr in Mariahilf.", "Gestern"),
                ChatMessage(7, 2, 1, "Passt. Soll ich Werkzeug mitbringen?", "Gestern"),
                ChatMessage(8, 2, 2, "Ein Akkuschrauber wäre sehr hilfreich.", "Heute"),
            )
        ),
        Chat(
            id = 3,
            listingId = 3,
            partnerId = 3,
            messages = listOf(
                ChatMessage(9, 3, 1, "Hallo Kali, ich habe ein Problem mit dem Waschbecken.", "Mo"),
                ChatMessage(10, 3, 3, "Kannst du mir ein Foto und den Bezirk schicken?", "Mo"),
                ChatMessage(11, 3, 1, "Donaustadt, der Ablauf ist undicht.", "Mo"),
                ChatMessage(12, 3, 3, "Ich kann morgen am Nachmittag vorbeikommen.", "Mo"),
            )
        ),
        Chat(
            id = 4,
            listingId = 4,
            partnerId = 4,
            messages = listOf(
                ChatMessage(13, 4, 1, "Hallo Luca, ich brauche Hilfe bei einer Kotlin-Aufgabe.", "So"),
                ChatMessage(14, 4, 4, "Gerne. Geht es um Compose oder Grundlagen?", "So"),
                ChatMessage(15, 4, 1, "Es geht um Navigation in Compose.", "So"),
                ChatMessage(16, 4, 4, "Das kann ich dir heute Abend erklären.", "So"),
            )
        ),
    )

    fun getUserById(id: Int) = users.find { it.id == id }
    fun getListingById(id: Int) = listings.find { it.id == id }
    fun getChatById(id: Int) = chats.find { it.id == id }
    fun getChatForListing(listingId: Int) = chats.find { it.listingId == listingId }

    fun addMessageToChat(chatId: Int, message: ChatMessage) {
        val index = chats.indexOfFirst { it.id == chatId }
        if (index == -1) return

        val chat = chats[index]
        chats[index] = chat.copy(messages = chat.messages + message)
    }
}
