package org.example.groovy;

class Singer {
    def firstName
    def lastName
    def birthDate

    String toString() {
        "($firstName,$lastName,$birthDate)"
    }
}

