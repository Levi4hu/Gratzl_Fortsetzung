package com.example.gratzl.data.model

import kotlinx.coroutines.flow.MutableStateFlow

object SampleData {

    val users = listOf(
        UserProfile(
            id = 1,
            name = "Sophie Huber",
            district = "Leopoldstadt",
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
            ),
            reviews = listOf(
                UserReview(
                    authorName = "Markus",
                    stars = 5.0f,
                    text = "Sehr zuverlässig und freundlich. Hat mir kurzfristig beim Einkaufen geholfen."
                ),
                UserReview(
                    authorName = "Anna",
                    stars = 4.8f,
                    text = "Schnelle Antwort und sehr angenehme Kommunikation."
                ),
                UserReview(
                    authorName = "Peter",
                    stars = 4.9f,
                    text = "Die Pflanzen wurden super gepflegt. Gerne wieder!"
                )
            )
        ),
        UserProfile(
            id = 2,
            name = "Markus Weber",
            district = "Leopoldstadt",
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
            ),
            reviews = listOf(
                UserReview(
                    authorName = "Sophie",
                    stars = 4.9f,
                    text = "Sehr freundlich und pünktlich. Die Hilfe war genau wie abgesprochen."
                ),
                UserReview(
                    authorName = "Luca",
                    stars = 5.0f,
                    text = "Hat schnell geantwortet und zuverlässig geholfen."
                ),
                UserReview(
                    authorName = "Maria",
                    stars = 4.8f,
                    text = "Gute Kommunikation und sehr hilfsbereit."
                )
            )
        ),
        UserProfile(
            id = 3,
            name = "Kali K.",
            district = "Donaustadt",
            bio = "Junger Familienvater mit Erfahrung mit Rohren.",
            rating = 4.2f,
            reviewCount = 5,
            responseTime = "3h",
            responseSpeedLabel = "SCHNELL",
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
            responseTime = "< 1h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Programmierung"),
                UserSkill("Kotlin"),
                UserSkill("Java")
            )
        ),
        UserProfile(
            id = 5,
            name = "Anna Müller",
            district = "Neubau",
            bio = "Kreative Künstlerin und Yogalehrerin aus dem 7. Bezirk.",
            rating = 4.7f,
            reviewCount = 20,
            responseTime = "1h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Yoga", "Kurse für Anfänger und Fortgeschrittene."),
                UserSkill("Malunterricht", "Aquarell und Acryl.")
            )
        ),
        UserProfile(
            id = 6,
            name = "Peter Gruber",
            district = "Favoriten",
            bio = "Pensionierter Elektriker mit 30 Jahren Erfahrung.",
            rating = 4.8f,
            reviewCount = 35,
            responseTime = "2h",
            responseSpeedLabel = "SCHNELL",
            skills = listOf(
                UserSkill("Elektroarbeiten", "Alle Elektroarbeiten in der Wohnung."),
                UserSkill("Reparaturen")
            )
        ),
        UserProfile(
            id = 7,
            name = "Maria Bauer",
            district = "Mariahilf",
            bio = "Studentin und leidenschaftliche Köchin.",
            rating = 4.6f,
            reviewCount = 9,
            responseTime = "< 1h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Kochen", "Österreichische und internationale Küche."),
                UserSkill("Backen")
            )
        ),
        UserProfile(
            id = 8,
            name = "Hans Novak",
            district = "Döbling",
            bio = "Hobbygärtner mit großem Garten im 19. Bezirk.",
            rating = 4.5f,
            reviewCount = 14,
            responseTime = "4h",
            responseSpeedLabel = "NORMAL",
            skills = listOf(
                UserSkill("Gartenarbeit", "Mähen, Schneiden, Pflanzen."),
                UserSkill("Pflanzenpflege")
            )
        ),
        UserProfile(
            id = 9,
            name = "Julia Steiner",
            district = "Währing",
            bio = "Musikerin und Gitarrenlehrerin.",
            rating = 5.0f,
            reviewCount = 18,
            responseTime = "1h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Gitarrenunterricht", "Klassik und Pop für alle Altersgruppen."),
                UserSkill("Klavierunterricht")
            )
        ),
        UserProfile(
            id = 10,
            name = "Thomas Fuchs",
            district = "Brigittenau",
            bio = "Maler und Lackierer mit eigenem Betrieb.",
            rating = 4.4f,
            reviewCount = 22,
            responseTime = "2h",
            responseSpeedLabel = "SCHNELL",
            skills = listOf(
                UserSkill("Malerarbeiten", "Innen- und Außenarbeiten."),
                UserSkill("Tapezieren")
            )
        )
    )

    val favouriteIds = MutableStateFlow<Set<Int>>(setOf(1, 2, 3))

    fun toggleFavourite(id: Int) {
        val updated = favouriteIds.value.toMutableSet()
        if (id in updated) updated.remove(id) else updated.add(id)
        favouriteIds.value = updated
    }

    val savedListings: List<Listing>
        get() = listings.filter { it.id in favouriteIds.value }

    var listings = mutableListOf<Listing>(
        // Innere Stadt
        Listing(id = 1, title = "Einkaufshilfe gesucht", description = "Brauche jemanden der wöchentlich für mich einkauft.", isOffer = false, category = "Haushalt", district = "Innere Stadt", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 2, title = "Wohnung reinigen", description = "Gründliche Endreinigung nach Auszug.", isOffer = true, category = "Haushalt", district = "Innere Stadt", priceType = PriceType.FIXED, price = 120.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 3, title = "Nachhilfe Englisch", description = "Erfahrene Lehrerin bietet Nachhilfe.", isOffer = true, category = "Bildung", district = "Innere Stadt", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 4, title = "Elektroarbeiten", description = "Steckdosen und Lichtschalter tauschen.", isOffer = true, category = "Handwerk", district = "Innere Stadt", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 5, title = "Suche Putzhilfe", description = "1x pro Woche Wohnung reinigen.", isOffer = false, category = "Haushalt", district = "Innere Stadt", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 6, title = "Gitarrenunterricht", description = "Lerne Gitarre für Anfänger.", isOffer = true, category = "Bildung", district = "Innere Stadt", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 7, title = "Suche Möbelaufbau", description = "IKEA Pakete aufbauen.", isOffer = false, category = "Haushalt", district = "Innere Stadt", priceType = PriceType.FIXED, price = 50.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 8, title = "Malerarbeiten", description = "Zimmer streichen.", isOffer = true, category = "Handwerk", district = "Innere Stadt", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 9, title = "Suche Nachhilfe Mathe", description = "Schüler 8. Klasse sucht Hilfe.", isOffer = false, category = "Bildung", district = "Innere Stadt", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 10, title = "Hund ausführen", description = "Täglich spazieren gehen.", isOffer = true, category = "Haushalt", district = "Innere Stadt", priceType = PriceType.PER_HOUR, price = 10.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),

        // Leopoldstadt
        Listing(id = 11, title = "Nachhilfestunden Mathematik", description = "Biete Nachhilfe für Schüler bis Klasse 10.", isOffer = true, category = "Bildung", district = "Leopoldstadt", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 12, title = "Biete Programmierhilfe", description = "Durch mein Uni Erfahrung in Programmieren kann ich einiges.", isOffer = true, category = "Bildung", district = "Leopoldstadt", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 13, title = "Katze betreuen", description = "Passe auf deine Katze auf während du im Urlaub bist.", isOffer = true, category = "Haushalt", district = "Leopoldstadt", priceType = PriceType.PER_HOUR, price = 8.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 14, title = "PC Reparatur", description = "Repariere PCs und Laptops aller Art.", isOffer = true, category = "Bildung", district = "Leopoldstadt", priceType = PriceType.FIXED, price = 45.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 15, title = "Suche Babysitter", description = "Für 2 Kinder am Wochenende.", isOffer = false, category = "Haushalt", district = "Leopoldstadt", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 16, title = "Fenster putzen", description = "Professionelles Fensterputzen.", isOffer = true, category = "Haushalt", district = "Leopoldstadt", priceType = PriceType.FIXED, price = 40.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 17, title = "Suche Elektrikerr", description = "Steckdose defekt.", isOffer = false, category = "Handwerk", district = "Leopoldstadt", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.TODAY, userId = 3),
        Listing(id = 18, title = "Yogakurs", description = "Für Anfänger und Fortgeschrittene.", isOffer = true, category = "Bildung", district = "Leopoldstadt", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 19, title = "Suche Gartenhilfe", description = "Herbstschnitt im Garten.", isOffer = false, category = "Garten", district = "Leopoldstadt", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 20, title = "Umzugshelfer", description = "Helfe beim Umzug.", isOffer = true, category = "Haushalt", district = "Leopoldstadt", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),

        // Landstraße
        Listing(id = 21, title = "Nachhilfe Deutsch", description = "Für Schüler der Mittelschule.", isOffer = true, category = "Bildung", district = "Landstraße", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 22, title = "Suche Umzugshilfe", description = "Kleiner Umzug innerhalb Wiens.", isOffer = false, category = "Haushalt", district = "Landstraße", priceType = PriceType.FIXED, price = 150.0, urgency = UrgencyTag.TODAY, userId = 6),
        Listing(id = 23, title = "Gartenarbeit", description = "Mähen und Pflanzen.", isOffer = true, category = "Garten", district = "Landstraße", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 24, title = "Rohrreparatur", description = "Schnelle Hilfe bei Rohrproblemen.", isOffer = true, category = "Handwerk", district = "Landstraße", priceType = PriceType.FIXED, price = 90.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 25, title = "Suche Klavierlehrer", description = "Anfänger sucht Lehrer.", isOffer = false, category = "Bildung", district = "Landstraße", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 26, title = "Babysitting", description = "Erfahrene Babysitterin.", isOffer = true, category = "Haushalt", district = "Landstraße", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 27, title = "Suche Putzhilfe", description = "Regelmäßige Reinigung.", isOffer = false, category = "Haushalt", district = "Landstraße", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 28, title = "Malerarbeiten", description = "Wohnung streichen.", isOffer = true, category = "Handwerk", district = "Landstraße", priceType = PriceType.FIXED, price = 200.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 29, title = "Suche Elektriker", description = "Licht funktioniert nicht.", isOffer = false, category = "Handwerk", district = "Landstraße", priceType = PriceType.FIXED, price = 70.0, urgency = UrgencyTag.TODAY, userId = 4),
        Listing(id = 30, title = "Kochkurs", description = "Österreichische Küche lernen.", isOffer = true, category = "Bildung", district = "Landstraße", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),

        // Wieden
        Listing(id = 31, title = "Suche Nachhilfe Physik", description = "Maturavorbereitung.", isOffer = false, category = "Bildung", district = "Wieden", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 32, title = "Fenster putzen", description = "Fensterputzen für Wohnungen.", isOffer = true, category = "Haushalt", district = "Wieden", priceType = PriceType.FIXED, price = 35.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 33, title = "Elektroarbeiten", description = "Kleinere Elektroarbeiten.", isOffer = true, category = "Handwerk", district = "Wieden", priceType = PriceType.FIXED, price = 65.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 34, title = "Suche Babysitter", description = "Für Kleinkind 2 Jahre.", isOffer = false, category = "Haushalt", district = "Wieden", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 35, title = "Yogakurs Anfänger", description = "Entspannung und Bewegung.", isOffer = true, category = "Bildung", district = "Wieden", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 36, title = "Suche Fahrradreparatur", description = "Bremsen defekt.", isOffer = false, category = "Handwerk", district = "Wieden", priceType = PriceType.FIXED, price = 25.0, urgency = UrgencyTag.TODAY, userId = 3),
        Listing(id = 37, title = "Hund ausführen", description = "Täglicher Spaziergang.", isOffer = true, category = "Haushalt", district = "Wieden", priceType = PriceType.PER_HOUR, price = 10.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 38, title = "Gitarrenunterricht", description = "Pop und Rock Gitarre.", isOffer = true, category = "Bildung", district = "Wieden", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 39, title = "Suche Maler", description = "Kinderzimmer streichen.", isOffer = false, category = "Handwerk", district = "Wieden", priceType = PriceType.FIXED, price = 150.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 40, title = "Pflanzenpflege", description = "Pflanzen gießen im Urlaub.", isOffer = true, category = "Garten", district = "Wieden", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 8),

        // Margareten
        Listing(id = 41, title = "Nachhilfe Chemie", description = "Für AHS Oberstufe.", isOffer = true, category = "Bildung", district = "Margareten", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 42, title = "Suche Umzugshilfe", description = "Brauche 3 Personen.", isOffer = false, category = "Haushalt", district = "Margareten", priceType = PriceType.FIXED, price = 180.0, urgency = UrgencyTag.TODAY, userId = 2),
        Listing(id = 43, title = "Rasenmähen", description = "Garten pflegen.", isOffer = true, category = "Garten", district = "Margareten", priceType = PriceType.PER_HOUR, price = 11.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 44, title = "Sanitär Reparatur", description = "Wasserhahn tropft.", isOffer = true, category = "Handwerk", district = "Margareten", priceType = PriceType.FIXED, price = 55.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 45, title = "Suche Yogakurs", description = "Privatkurs für Zuhause.", isOffer = false, category = "Bildung", district = "Margareten", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 46, title = "Wohnung reinigen", description = "Endreinigung nach Mietende.", isOffer = true, category = "Haushalt", district = "Margareten", priceType = PriceType.FIXED, price = 100.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 47, title = "Suche Gitarrenlehrer", description = "Teenager Anfänger.", isOffer = false, category = "Bildung", district = "Margareten", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 48, title = "Elektroarbeiten", description = "Sicherungskasten prüfen.", isOffer = true, category = "Handwerk", district = "Margareten", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 49, title = "Suche Putzhilfe", description = "2x monatlich reinigen.", isOffer = false, category = "Haushalt", district = "Margareten", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 50, title = "Kochkurs Backen", description = "Österreichische Mehlspeisen.", isOffer = true, category = "Bildung", district = "Margareten", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),

        // Mariahilf
        Listing(id = 51, title = "Suche Umzugshilfe", description = "Brauche 2 Personen für einen Samstag.", isOffer = false, category = "Haushalt", district = "Mariahilf", priceType = PriceType.TRADE, urgency = UrgencyTag.TODAY, userId = 2),
        Listing(id = 52, title = "Suche Nachhilfe Chemie", description = "Schüler der 7. Klasse.", isOffer = false, category = "Bildung", district = "Mariahilf", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 53, title = "Suche Gartenhelfer", description = "Frühjahrspflege für Garten.", isOffer = false, category = "Garten", district = "Mariahilf", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 54, title = "Malerarbeiten", description = "Wohnung komplett streichen.", isOffer = true, category = "Handwerk", district = "Mariahilf", priceType = PriceType.FIXED, price = 300.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 55, title = "Babysitting", description = "Erfahrene Betreuung.", isOffer = true, category = "Haushalt", district = "Mariahilf", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 56, title = "Gitarrenunterricht", description = "Klassische Gitarre.", isOffer = true, category = "Bildung", district = "Mariahilf", priceType = PriceType.PER_HOUR, price = 24.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 57, title = "Suche Elektriker", description = "Deckenlampe montieren.", isOffer = false, category = "Handwerk", district = "Mariahilf", priceType = PriceType.FIXED, price = 50.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 58, title = "Pflanzenpflege", description = "Balkonpflanzen gießen.", isOffer = true, category = "Garten", district = "Mariahilf", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 59, title = "Suche Reinigung", description = "Wöchentliche Putzhilfe.", isOffer = false, category = "Haushalt", district = "Mariahilf", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 60, title = "Nachhilfe Englisch", description = "Business English Kurs.", isOffer = true, category = "Bildung", district = "Mariahilf", priceType = PriceType.PER_HOUR, price = 28.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),

        // Neubau
        Listing(id = 61, title = "Gartenarbeit anbieten", description = "Helfe beim Mähen und Aufräumen.", isOffer = true, category = "Garten", district = "Neubau", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 62, title = "Yoga Unterricht", description = "Für Anfänger und Fortgeschrittene.", isOffer = true, category = "Bildung", district = "Neubau", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 63, title = "Suche Gitarrenlehrer", description = "Teenager sucht Unterricht.", isOffer = false, category = "Bildung", district = "Neubau", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 64, title = "Malunterricht", description = "Aquarell und Ölmalerei.", isOffer = true, category = "Bildung", district = "Neubau", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 65, title = "Suche Putzhilfe", description = "Büro reinigen 1x wöchentlich.", isOffer = false, category = "Haushalt", district = "Neubau", priceType = PriceType.PER_HOUR, price = 16.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 66, title = "Elektroarbeiten", description = "Neue Steckdosen installieren.", isOffer = true, category = "Handwerk", district = "Neubau", priceType = PriceType.FIXED, price = 70.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 67, title = "Suche Möbelaufbau", description = "Schrank und Bett aufbauen.", isOffer = false, category = "Haushalt", district = "Neubau", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 68, title = "Hund ausführen", description = "Tägliche Runde im Park.", isOffer = true, category = "Haushalt", district = "Neubau", priceType = PriceType.PER_HOUR, price = 10.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 69, title = "Suche Nachhilfe Geschichte", description = "Matura in 3 Monaten.", isOffer = false, category = "Bildung", district = "Neubau", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 70, title = "Fahrradreparatur", description = "Alle Reparaturen am Fahrrad.", isOffer = true, category = "Handwerk", district = "Neubau", priceType = PriceType.FIXED, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),

        // Josefstadt
        Listing(id = 71, title = "Suche Nachhilfe Englisch", description = "Für Tochter in der 5. Klasse.", isOffer = false, category = "Bildung", district = "Josefstadt", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 72, title = "Elektroarbeiten", description = "Kleine Elektroarbeiten.", isOffer = true, category = "Handwerk", district = "Josefstadt", priceType = PriceType.FIXED, price = 70.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 73, title = "Malunterricht", description = "Aquarell für Erwachsene.", isOffer = true, category = "Bildung", district = "Josefstadt", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 74, title = "Suche Möbelaufbau", description = "3 IKEA Pakete.", isOffer = false, category = "Haushalt", district = "Josefstadt", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 75, title = "Gartenarbeit", description = "Hecke schneiden.", isOffer = true, category = "Garten", district = "Josefstadt", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 76, title = "Suche Reinigung", description = "Endreinigung Wohnung.", isOffer = false, category = "Haushalt", district = "Josefstadt", priceType = PriceType.FIXED, price = 120.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 77, title = "Gitarrenunterricht", description = "Rock und Blues Gitarre.", isOffer = true, category = "Bildung", district = "Josefstadt", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 78, title = "Suche Gartenhilfe", description = "Frühjahrspflege.", isOffer = false, category = "Garten", district = "Josefstadt", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 79, title = "Babysitting", description = "Für Kinder ab 1 Jahr.", isOffer = true, category = "Haushalt", district = "Josefstadt", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 80, title = "Suche Nachhilfe Biologie", description = "Für Matura.", isOffer = false, category = "Bildung", district = "Josefstadt", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),

        // Alsergrund
        Listing(id = 81, title = "Möbel aufbauen", description = "IKEA Möbel aufgebaut.", isOffer = true, category = "Haushalt", district = "Alsergrund", priceType = PriceType.FIXED, price = 50.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 82, title = "Suche Babysitter", description = "Für Wochenende.", isOffer = false, category = "Haushalt", district = "Alsergrund", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 83, title = "Nachhilfe Mathematik", description = "Uni Mathe Grundlagen.", isOffer = true, category = "Bildung", district = "Alsergrund", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 84, title = "Malerarbeiten", description = "Wohnung renovieren.", isOffer = true, category = "Handwerk", district = "Alsergrund", priceType = PriceType.FIXED, price = 250.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 85, title = "Suche Elektriker", description = "Sicherung ausgefallen.", isOffer = false, category = "Handwerk", district = "Alsergrund", priceType = PriceType.FIXED, price = 70.0, urgency = UrgencyTag.TODAY, userId = 5),
        Listing(id = 86, title = "Yogakurs", description = "Morgenroutine Yoga.", isOffer = true, category = "Bildung", district = "Alsergrund", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 87, title = "Suche Kochkurs", description = "Asiatische Küche lernen.", isOffer = false, category = "Bildung", district = "Alsergrund", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 88, title = "Pflanzenpflege", description = "Urlaub Pflanzen gießen.", isOffer = true, category = "Garten", district = "Alsergrund", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 89, title = "Suche Umzugshelfer", description = "Kleinmöbel transportieren.", isOffer = false, category = "Haushalt", district = "Alsergrund", priceType = PriceType.FIXED, price = 100.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 90, title = "Fahrradreparatur", description = "Reifenwechsel und Service.", isOffer = true, category = "Handwerk", district = "Alsergrund", priceType = PriceType.FIXED, price = 30.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),

        // Favoriten
        Listing(id = 91, title = "Nachhilfestunden Mathematik", description = "Schüler bis Klasse 10.", isOffer = true, category = "Bildung", district = "Favoriten", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 92, title = "Suche Umzugshelfer", description = "Kleiner Umzug.", isOffer = false, category = "Haushalt", district = "Favoriten", priceType = PriceType.FIXED, price = 150.0, urgency = UrgencyTag.TODAY, userId = 4),
        Listing(id = 93, title = "Hecke schneiden", description = "Hecken und Sträucher.", isOffer = true, category = "Garten", district = "Favoriten", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 94, title = "Elektroarbeiten", description = "Lichtanlage erneuern.", isOffer = true, category = "Handwerk", district = "Favoriten", priceType = PriceType.FIXED, price = 90.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 95, title = "Suche Putzhilfe", description = "Große Wohnung reinigen.", isOffer = false, category = "Haushalt", district = "Favoriten", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 96, title = "Kochkurs", description = "Türkische Küche.", isOffer = true, category = "Bildung", district = "Favoriten", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 97, title = "Suche Fahrradreparatur", description = "Schaltung defekt.", isOffer = false, category = "Handwerk", district = "Favoriten", priceType = PriceType.FIXED, price = 30.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 98, title = "Hund ausführen", description = "Spaziergang täglich.", isOffer = true, category = "Haushalt", district = "Favoriten", priceType = PriceType.PER_HOUR, price = 10.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 99, title = "Suche Nachhilfe Englisch", description = "Business Englisch.", isOffer = false, category = "Bildung", district = "Favoriten", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 100, title = "Malerarbeiten", description = "Renovierung komplett.", isOffer = true, category = "Handwerk", district = "Favoriten", priceType = PriceType.FIXED, price = 350.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),

        // Simmering
        Listing(id = 101, title = "Suche Elektriker", description = "Steckdose defekt.", isOffer = false, category = "Handwerk", district = "Simmering", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.TODAY, userId = 2),
        Listing(id = 102, title = "Suche Fahrradreparatur", description = "Kette gerissen.", isOffer = false, category = "Handwerk", district = "Simmering", priceType = PriceType.FIXED, price = 20.0, urgency = UrgencyTag.TODAY, userId = 1),
        Listing(id = 103, title = "Gartenarbeit", description = "Großen Garten pflegen.", isOffer = true, category = "Garten", district = "Simmering", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 104, title = "Nachhilfe Geschichte", description = "AHS Matura.", isOffer = true, category = "Bildung", district = "Simmering", priceType = PriceType.PER_HOUR, price = 16.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 105, title = "Suche Putzhilfe", description = "Wöchentlich reinigen.", isOffer = false, category = "Haushalt", district = "Simmering", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 106, title = "Malerarbeiten", description = "Kellerräume streichen.", isOffer = true, category = "Handwerk", district = "Simmering", priceType = PriceType.FIXED, price = 120.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 107, title = "Suche Yogakurs", description = "Stressabbau Yoga.", isOffer = false, category = "Bildung", district = "Simmering", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 108, title = "Möbel aufbauen", description = "Schlafzimmer komplett.", isOffer = true, category = "Haushalt", district = "Simmering", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 109, title = "Suche Nachhilfe Deutsch", description = "Für Hauptschulabschluss.", isOffer = false, category = "Bildung", district = "Simmering", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 110, title = "Elektroarbeiten", description = "Outdoor Beleuchtung.", isOffer = true, category = "Handwerk", district = "Simmering", priceType = PriceType.FIXED, price = 100.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),

        // Meidling
        Listing(id = 111, title = "Deutschkurs Anfänger", description = "Für Nicht-Muttersprachler.", isOffer = true, category = "Bildung", district = "Meidling", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 112, title = "Wohnung reinigen", description = "Endreinigung Mietende.", isOffer = true, category = "Haushalt", district = "Meidling", priceType = PriceType.FIXED, price = 120.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 113, title = "Suche Yogalehrer", description = "Privatkurs Zuhause.", isOffer = false, category = "Bildung", district = "Meidling", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 114, title = "Rohrreparatur", description = "Wasserrohr defekt.", isOffer = true, category = "Handwerk", district = "Meidling", priceType = PriceType.FIXED, price = 90.0, urgency = UrgencyTag.TODAY, userId = 3),
        Listing(id = 115, title = "Suche Gartenhilfe", description = "Herbstschnitt.", isOffer = false, category = "Garten", district = "Meidling", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 116, title = "Babysitting", description = "Kinder ab 2 Jahren.", isOffer = true, category = "Haushalt", district = "Meidling", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 117, title = "Suche Elektriker", description = "Neue Leitungen legen.", isOffer = false, category = "Handwerk", district = "Meidling", priceType = PriceType.FIXED, price = 120.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 118, title = "Gitarrenunterricht", description = "Alle Stilrichtungen.", isOffer = true, category = "Bildung", district = "Meidling", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 119, title = "Suche Maler", description = "Badezimmer streichen.", isOffer = false, category = "Handwerk", district = "Meidling", priceType = PriceType.FIXED, price = 180.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 120, title = "Pflanzenpflege Urlaub", description = "2 Wochen gießen.", isOffer = true, category = "Garten", district = "Meidling", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 8),

        // Hietzing
        Listing(id = 121, title = "Fenster putzen", description = "Villa mit vielen Fenstern.", isOffer = true, category = "Haushalt", district = "Hietzing", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 122, title = "Suche Klavierlehrer", description = "Für Erwachsene.", isOffer = false, category = "Bildung", district = "Hietzing", priceType = PriceType.PER_HOUR, price = 30.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 123, title = "Suche Yogalehrer", description = "2 Personen Privatkurs.", isOffer = false, category = "Bildung", district = "Hietzing", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 124, title = "Gartenarbeit", description = "Großer Villengarten.", isOffer = true, category = "Garten", district = "Hietzing", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 125, title = "Elektroarbeiten", description = "Smart Home Installation.", isOffer = true, category = "Handwerk", district = "Hietzing", priceType = PriceType.FIXED, price = 200.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 126, title = "Suche Reinigung", description = "Großes Haus reinigen.", isOffer = false, category = "Haushalt", district = "Hietzing", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 127, title = "Sanitär Reparatur", description = "Badewanne abdichten.", isOffer = true, category = "Handwerk", district = "Hietzing", priceType = PriceType.FIXED, price = 70.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 128, title = "Suche Nachhilfe Mathe", description = "Gymnasium Oberstufe.", isOffer = false, category = "Bildung", district = "Hietzing", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 129, title = "Hund ausführen", description = "Großer Hund braucht viel Bewegung.", isOffer = true, category = "Haushalt", district = "Hietzing", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 130, title = "Suche Maler", description = "Altbau renovieren.", isOffer = false, category = "Handwerk", district = "Hietzing", priceType = PriceType.FIXED, price = 400.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),

        // Penzing
        Listing(id = 131, title = "Suche Klavierlehrer", description = "Anfänger Kind 8 Jahre.", isOffer = false, category = "Bildung", district = "Penzing", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 132, title = "Pflanzenpflege", description = "Zimmerpflanzen gießen.", isOffer = true, category = "Garten", district = "Penzing", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 133, title = "Nachhilfe Physik", description = "Matura Vorbereitung.", isOffer = true, category = "Bildung", district = "Penzing", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 134, title = "Suche Putzhilfe", description = "Regelmäßig 2x Monat.", isOffer = false, category = "Haushalt", district = "Penzing", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 135, title = "Elektroarbeiten", description = "Garage elektrifizieren.", isOffer = true, category = "Handwerk", district = "Penzing", priceType = PriceType.FIXED, price = 150.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 136, title = "Suche Umzugshelfer", description = "Schwere Möbel.", isOffer = false, category = "Haushalt", district = "Penzing", priceType = PriceType.FIXED, price = 130.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 137, title = "Gartenarbeit", description = "Obstbäume schneiden.", isOffer = true, category = "Garten", district = "Penzing", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 138, title = "Suche Nachhilfe Englisch", description = "Für Aufnahmetest.", isOffer = false, category = "Bildung", district = "Penzing", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 139, title = "Fahrradreparatur", description = "E-Bike Service.", isOffer = true, category = "Handwerk", district = "Penzing", priceType = PriceType.FIXED, price = 40.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 140, title = "Suche Maler", description = "Kinderzimmer neu gestalten.", isOffer = false, category = "Handwerk", district = "Penzing", priceType = PriceType.FIXED, price = 200.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),

        // Rudolfsheim-Fünfhaus
        Listing(id = 141, title = "Auto waschen", description = "Von Hand inkl. Innenreinigung.", isOffer = true, category = "Handwerk", district = "Rudolfsheim", priceType = PriceType.FIXED, price = 30.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 142, title = "Suche Kochkurs", description = "Asiatische Küche.", isOffer = false, category = "Bildung", district = "Rudolfsheim", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 143, title = "Nachhilfe Deutsch", description = "Für Volksschule.", isOffer = true, category = "Bildung", district = "Rudolfsheim", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 144, title = "Suche Elektriker", description = "Defekte Steckdosen.", isOffer = false, category = "Handwerk", district = "Rudolfsheim", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 145, title = "Gartenarbeit", description = "Rasenmähen.", isOffer = true, category = "Garten", district = "Rudolfsheim", priceType = PriceType.PER_HOUR, price = 11.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 146, title = "Suche Reinigung", description = "Büroreinigung.", isOffer = false, category = "Haushalt", district = "Rudolfsheim", priceType = PriceType.PER_HOUR, price = 16.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 147, title = "Yogakurs", description = "Power Yoga.", isOffer = true, category = "Bildung", district = "Rudolfsheim", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 148, title = "Suche Babysitter", description = "Abends und Wochenende.", isOffer = false, category = "Haushalt", district = "Rudolfsheim", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 149, title = "Malerarbeiten", description = "Wohnzimmer streichen.", isOffer = true, category = "Handwerk", district = "Rudolfsheim", priceType = PriceType.FIXED, price = 180.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 150, title = "Suche Fahrradreparatur", description = "Reifenpanne.", isOffer = false, category = "Handwerk", district = "Rudolfsheim", priceType = PriceType.FIXED, price = 15.0, urgency = UrgencyTag.TODAY, userId = 7),

        // Ottakring
        Listing(id = 151, title = "Kochkurs österreichisch", description = "Wiener Schnitzel und Co.", isOffer = true, category = "Bildung", district = "Ottakring", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 152, title = "Schlüsseldienst", description = "Türen öffnen.", isOffer = true, category = "Handwerk", district = "Ottakring", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.TODAY, userId = 6),
        Listing(id = 153, title = "Suche Putzhilfe", description = "Großfamilie sucht Hilfe.", isOffer = false, category = "Haushalt", district = "Ottakring", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 154, title = "Nachhilfe Mathematik", description = "Volksschule bis AHS.", isOffer = true, category = "Bildung", district = "Ottakring", priceType = PriceType.PER_HOUR, price = 17.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 155, title = "Suche Umzugshelfer", description = "Wohnungsauflösung.", isOffer = false, category = "Haushalt", district = "Ottakring", priceType = PriceType.FIXED, price = 200.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 156, title = "Gartenarbeit", description = "Stadtgarten pflegen.", isOffer = true, category = "Garten", district = "Ottakring", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 157, title = "Suche Nachhilfe Englisch", description = "Bewerbungsgespräch vorbereiten.", isOffer = false, category = "Bildung", district = "Ottakring", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 158, title = "Elektroarbeiten", description = "Wohnung neu verkabeln.", isOffer = true, category = "Handwerk", district = "Ottakring", priceType = PriceType.FIXED, price = 300.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 159, title = "Suche Gitarrenlehrer", description = "Flamenco Gitarre lernen.", isOffer = false, category = "Bildung", district = "Ottakring", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 160, title = "Hund ausführen", description = "2x täglich.", isOffer = true, category = "Haushalt", district = "Ottakring", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),

        // Hernals
        Listing(id = 161, title = "Suche Putzhilfe", description = "Penthouse reinigen.", isOffer = false, category = "Haushalt", district = "Hernals", priceType = PriceType.PER_HOUR, price = 16.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 162, title = "Suche Hundebetreuung", description = "Tägliche Betreuung.", isOffer = false, category = "Haushalt", district = "Hernals", priceType = PriceType.PER_HOUR, price = 10.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 163, title = "Nachhilfe Geschichte", description = "Matura Vorbereitung.", isOffer = true, category = "Bildung", district = "Hernals", priceType = PriceType.PER_HOUR, price = 16.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 164, title = "Gartenarbeit", description = "Balkon bepflanzen.", isOffer = true, category = "Garten", district = "Hernals", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 165, title = "Suche Elektriker", description = "Außenbeleuchtung montieren.", isOffer = false, category = "Handwerk", district = "Hernals", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 166, title = "Yogakurs", description = "Abendkurs entspannen.", isOffer = true, category = "Bildung", district = "Hernals", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 167, title = "Suche Nachhilfe Physik", description = "Für Aufnahmeprüfung.", isOffer = false, category = "Bildung", district = "Hernals", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 168, title = "Möbel aufbauen", description = "Ikea Lieferung aufbauen.", isOffer = true, category = "Haushalt", district = "Hernals", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 169, title = "Suche Maler", description = "Wohnung renovieren.", isOffer = false, category = "Handwerk", district = "Hernals", priceType = PriceType.FIXED, price = 250.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 170, title = "Kochkurs", description = "Italienische Küche.", isOffer = true, category = "Bildung", district = "Hernals", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),

        // Währing
        Listing(id = 171, title = "Hund ausführen", description = "Tägliche Spaziergänge.", isOffer = true, category = "Haushalt", district = "Währing", priceType = PriceType.PER_HOUR, price = 10.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 172, title = "Suche Maler", description = "Ganze Wohnung streichen.", isOffer = false, category = "Handwerk", district = "Währing", priceType = PriceType.FIXED, price = 200.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 173, title = "Suche Nachhilfe Biologie", description = "Matura Biologie.", isOffer = false, category = "Bildung", district = "Währing", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 174, title = "Gitarrenunterricht", description = "Klassische Gitarre.", isOffer = true, category = "Bildung", district = "Währing", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 175, title = "Suche Putzhilfe", description = "Altbauwohnung reinigen.", isOffer = false, category = "Haushalt", district = "Währing", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 176, title = "Gartenarbeit", description = "Rasenpflege.", isOffer = true, category = "Garten", district = "Währing", priceType = PriceType.PER_HOUR, price = 11.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 177, title = "Suche Elektriker", description = "Sicherungskasten prüfen.", isOffer = false, category = "Handwerk", district = "Währing", priceType = PriceType.FIXED, price = 70.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 178, title = "Nachhilfe Englisch", description = "Cambridge Prüfung vorbereiten.", isOffer = true, category = "Bildung", district = "Währing", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 179, title = "Suche Yogakurs", description = "Morgens vor Arbeit.", isOffer = false, category = "Bildung", district = "Währing", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 180, title = "Sanitär Reparatur", description = "Dusche tropft.", isOffer = true, category = "Handwerk", district = "Währing", priceType = PriceType.FIXED, price = 60.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),

        // Döbling
        Listing(id = 181, title = "Babysitting anbieten", description = "Kinder ab 1 Jahr.", isOffer = true, category = "Haushalt", district = "Döbling", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 182, title = "Rasenmähen", description = "Großen Garten mähen.", isOffer = true, category = "Garten", district = "Döbling", priceType = PriceType.PER_HOUR, price = 11.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 183, title = "Sanitär Reparatur", description = "Armaturen wechseln.", isOffer = true, category = "Handwerk", district = "Döbling", priceType = PriceType.FIXED, price = 55.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 184, title = "Suche Nachhilfe Mathe", description = "HTL Vorbereitung.", isOffer = false, category = "Bildung", district = "Döbling", priceType = PriceType.PER_HOUR, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 185, title = "Elektroarbeiten", description = "Pool elektrisch anschließen.", isOffer = true, category = "Handwerk", district = "Döbling", priceType = PriceType.FIXED, price = 180.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 186, title = "Suche Reinigung", description = "Villa reinigen wöchentlich.", isOffer = false, category = "Haushalt", district = "Döbling", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 187, title = "Nachhilfe Latein", description = "Gymnasium Latein.", isOffer = true, category = "Bildung", district = "Döbling", priceType = PriceType.PER_HOUR, price = 28.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 188, title = "Suche Yogalehrer", description = "Privatstunden.", isOffer = false, category = "Bildung", district = "Döbling", priceType = PriceType.PER_HOUR, price = 30.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 189, title = "Klavierunterricht", description = "Klassisches Klavier.", isOffer = true, category = "Bildung", district = "Döbling", priceType = PriceType.PER_HOUR, price = 30.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 190, title = "Suche Gartenpflege", description = "Großer Garten regelmäßig.", isOffer = false, category = "Garten", district = "Döbling", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),

        // Brigittenau
        Listing(id = 191, title = "Malerarbeiten", description = "Zimmer streichen.", isOffer = true, category = "Handwerk", district = "Brigittenau", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 192, title = "Gitarrenunterricht", description = "Für Einsteiger.", isOffer = true, category = "Bildung", district = "Brigittenau", priceType = PriceType.PER_HOUR, price = 22.0, urgency = UrgencyTag.FLEXIBLE, userId = 9),
        Listing(id = 193, title = "Blumen gießen Urlaub", description = "Balkonpflanzen 2 Wochen.", isOffer = true, category = "Garten", district = "Brigittenau", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 194, title = "Suche Nachhilfe Englisch", description = "Berufsschule.", isOffer = false, category = "Bildung", district = "Brigittenau", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 195, title = "Möbel aufbauen", description = "Schnell und sauber.", isOffer = true, category = "Haushalt", district = "Brigittenau", priceType = PriceType.FIXED, price = 55.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 196, title = "Suche Elektriker", description = "Küche neu elektrifizieren.", isOffer = false, category = "Handwerk", district = "Brigittenau", priceType = PriceType.FIXED, price = 150.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 197, title = "Yogakurs", description = "Wöchentlicher Kurs.", isOffer = true, category = "Bildung", district = "Brigittenau", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 198, title = "Suche Putzhilfe", description = "Wöchentlich.", isOffer = false, category = "Haushalt", district = "Brigittenau", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 199, title = "Fahrradreparatur", description = "Alle Marken.", isOffer = true, category = "Handwerk", district = "Brigittenau", priceType = PriceType.FIXED, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 200, title = "Suche Kochkurs", description = "Arabische Küche.", isOffer = false, category = "Bildung", district = "Brigittenau", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),

        // Floridsdorf
        Listing(id = 201, title = "Fahrradreparatur", description = "Alle Fahrradtypen.", isOffer = true, category = "Handwerk", district = "Floridsdorf", priceType = PriceType.FIXED, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 202, title = "Nachhilfe Geschichte", description = "AHS Matura.", isOffer = true, category = "Bildung", district = "Floridsdorf", priceType = PriceType.PER_HOUR, price = 16.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 203, title = "Suche Gartenhilfe", description = "Großer Garten.", isOffer = false, category = "Garten", district = "Floridsdorf", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 204, title = "Malerarbeiten", description = "Außenfassade streichen.", isOffer = true, category = "Handwerk", district = "Floridsdorf", priceType = PriceType.FIXED, price = 500.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),
        Listing(id = 205, title = "Suche Putzhilfe", description = "Einfamilienhaus.", isOffer = false, category = "Haushalt", district = "Floridsdorf", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 206, title = "Elektroarbeiten", description = "Solaranlage anschließen.", isOffer = true, category = "Handwerk", district = "Floridsdorf", priceType = PriceType.FIXED, price = 250.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 207, title = "Suche Babysitter", description = "Für Kleinkind.", isOffer = false, category = "Haushalt", district = "Floridsdorf", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 208, title = "Nachhilfe Mathe", description = "HTL Mathematik.", isOffer = true, category = "Bildung", district = "Floridsdorf", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 209, title = "Suche Elektriker dringend", description = "Kurzschluss Küche.", isOffer = false, category = "Handwerk", district = "Floridsdorf", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.TODAY, userId = 7),
        Listing(id = 210, title = "Gartenarbeit", description = "Gemüsegarten anlegen.", isOffer = true, category = "Garten", district = "Floridsdorf", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),

        // Donaustadt
        Listing(id = 211, title = "Biete Rohrreparatur", description = "Schnelle Hilfe bei defekten Rohren.", isOffer = true, category = "Handwerk", district = "Donaustadt", priceType = PriceType.FIXED, price = 100.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 212, title = "Nachhilfe Physik", description = "Maturavorbereitung.", isOffer = true, category = "Bildung", district = "Donaustadt", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 213, title = "Suche Wohnungsreinigung", description = "2x pro Monat.", isOffer = false, category = "Haushalt", district = "Donaustadt", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 214, title = "Gartenarbeit", description = "Reihenhausgarten pflegen.", isOffer = true, category = "Garten", district = "Donaustadt", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 215, title = "Suche Nachhilfe Deutsch", description = "Deutsch als Fremdsprache.", isOffer = false, category = "Bildung", district = "Donaustadt", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 216, title = "Elektroarbeiten", description = "Neue Steckdosen.", isOffer = true, category = "Handwerk", district = "Donaustadt", priceType = PriceType.FIXED, price = 70.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 217, title = "Suche Umzugshelfer", description = "Neue Wohnung beziehen.", isOffer = false, category = "Haushalt", district = "Donaustadt", priceType = PriceType.FIXED, price = 160.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 218, title = "Babysitting", description = "Erfahrene Betreuung.", isOffer = true, category = "Haushalt", district = "Donaustadt", priceType = PriceType.PER_HOUR, price = 14.0, urgency = UrgencyTag.FLEXIBLE, userId = 1),
        Listing(id = 219, title = "Suche Yogakurs", description = "Outdoor Yoga.", isOffer = false, category = "Bildung", district = "Donaustadt", priceType = PriceType.PER_HOUR, price = 20.0, urgency = UrgencyTag.FLEXIBLE, userId = 7),
        Listing(id = 220, title = "Malerarbeiten", description = "Neubau streichen.", isOffer = true, category = "Handwerk", district = "Donaustadt", priceType = PriceType.FIXED, price = 400.0, urgency = UrgencyTag.FLEXIBLE, userId = 10),

        // Liesing
        Listing(id = 221, title = "Suche Gartenhilfe", description = "Herbstschnitt.", isOffer = false, category = "Garten", district = "Liesing", priceType = PriceType.PER_HOUR, price = 12.0, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 222, title = "Suche Elektriker dringend", description = "Kurzschluss.", isOffer = false, category = "Handwerk", district = "Liesing", priceType = PriceType.FIXED, price = 80.0, urgency = UrgencyTag.TODAY, userId = 1),
        Listing(id = 223, title = "Einkaufen helfen", description = "Ältere Menschen unterstützen.", isOffer = true, category = "Haushalt", district = "Liesing", priceType = PriceType.COFFEE, urgency = UrgencyTag.FLEXIBLE, userId = 2),
        Listing(id = 224, title = "Nachhilfe Mathe", description = "Für Volksschule.", isOffer = true, category = "Bildung", district = "Liesing", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 225, title = "Suche Putzhilfe", description = "Einfamilienhaus.", isOffer = false, category = "Haushalt", district = "Liesing", priceType = PriceType.PER_HOUR, price = 15.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
        Listing(id = 226, title = "Gartenarbeit", description = "Großen Garten pflegen.", isOffer = true, category = "Garten", district = "Liesing", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 8),
        Listing(id = 227, title = "Suche Maler", description = "Renovierung.", isOffer = false, category = "Handwerk", district = "Liesing", priceType = PriceType.FIXED, price = 300.0, urgency = UrgencyTag.FLEXIBLE, userId = 4),
        Listing(id = 228, title = "Yogakurs", description = "Abendkurs.", isOffer = true, category = "Bildung", district = "Liesing", priceType = PriceType.PER_HOUR, price = 18.0, urgency = UrgencyTag.FLEXIBLE, userId = 5),
        Listing(id = 229, title = "Suche Babysitter", description = "Regelmäßig Abends.", isOffer = false, category = "Haushalt", district = "Liesing", priceType = PriceType.PER_HOUR, price = 13.0, urgency = UrgencyTag.FLEXIBLE, userId = 6),
        Listing(id = 230, title = "Fahrradreparatur", description = "Alle Reparaturen.", isOffer = true, category = "Handwerk", district = "Liesing", priceType = PriceType.FIXED, price = 25.0, urgency = UrgencyTag.FLEXIBLE, userId = 3),
    )

    val chats = mutableListOf(
        Chat(
            id = 1,
            listingId = 1,
            partnerId = 2,
            messages = listOf(
                ChatMessage(1, 1, 2, "Hallo Sophie, ich kann dir beim wöchentlichen Einkauf helfen.", "14:30"),
                ChatMessage(2, 1, 1, "Danke Markus, das wäre super. Es geht meistens um Lebensmittel.", "14:32"),
                ChatMessage(3, 1, 2, "Passt gut. Soll ich am Freitagvormittag vorbeikommen?", "14:35"),
                ChatMessage(4, 1, 1, "Freitag passt. Ich schreibe dir vorher die Einkaufsliste.", "14:38"),
            )
        ),
        Chat(
            id = 2,
            listingId = 2,
            partnerId = 2,
            messages = listOf(
                ChatMessage(5, 2, 1, "Hallo Markus, ich interessiere mich für die Wohnungsreinigung.", "Gestern"),
                ChatMessage(6, 2, 2, "Gerne, es geht um eine gründliche Endreinigung nach dem Auszug.", "Gestern"),
                ChatMessage(7, 2, 1, "Kannst du auch Küche und Bad komplett übernehmen?", "Gestern"),
                ChatMessage(8, 2, 2, "Ja, das ist im Preis dabei. Ich bringe die Reinigungsmittel mit.", "Heute"),
            )
        ),
        Chat(
            id = 3,
            listingId = 3,
            partnerId = 5,
            messages = listOf(
                ChatMessage(9, 3, 1, "Hallo Anna, ich suche Unterstützung in Englisch.", "Mo"),
                ChatMessage(10, 3, 5, "Sehr gerne. Für welche Schulstufe ist die Nachhilfe?", "Mo"),
                ChatMessage(11, 3, 1, "Für die 8. Klasse, vor allem Grammatik und Schreiben.", "Mo"),
                ChatMessage(12, 3, 5, "Das passt gut. Ich kann am Mittwoch eine Probestunde anbieten.", "Mo"),
            )
        ),
        Chat(
            id = 4,
            listingId = 4,
            partnerId = 6,
            messages = listOf(
                ChatMessage(13, 4, 1, "Hallo Peter, ich brauche Hilfe mit zwei defekten Steckdosen.", "So"),
                ChatMessage(14, 4, 6, "Kann ich mir anschauen. Ist der Stromkreis schon ausgeschaltet?", "So"),
                ChatMessage(15, 4, 1, "Ja, die Sicherung ist draußen. Es ist in der Küche.", "So"),
                ChatMessage(16, 4, 6, "Gut, dann komme ich morgen am Vormittag vorbei.", "So"),
            )
        ),
        Chat(
            id = 5,
            listingId = 11,
            partnerId = 2,
            messages = listOf(
                ChatMessage(17, 5, 2, "Hallo Sophie, bietest du noch Mathe-Nachhilfe an?", "09:10"),
                ChatMessage(18, 5, 1, "Ja, ich habe nächste Woche noch zwei freie Termine.", "09:14"),
                ChatMessage(19, 5, 2, "Super. Es geht um Brüche und Gleichungen, 6. Klasse.", "09:18"),
                ChatMessage(20, 5, 1, "Das passt gut. Dienstag um 16 Uhr wäre möglich.", "09:22"),
            )
        ),
        Chat(
            id = 6,
            listingId = 7,
            partnerId = 3,
            messages = listOf(
                ChatMessage(21, 6, 1, "Hallo Kali, ich kann dir beim IKEA Möbelaufbau helfen.", "Heute"),
                ChatMessage(22, 6, 3, "Danke Sophie. Es sind ein Kleiderschrank und ein kleines Regal.", "Heute"),
                ChatMessage(23, 6, 1, "Kein Problem. Hast du schon das passende Werkzeug vor Ort?", "Heute"),
                ChatMessage(24, 6, 3, "Schraubenzieher habe ich, einen Akkuschrauber leider nicht.", "Heute"),
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
