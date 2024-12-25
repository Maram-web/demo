Feature: Test de l'API utilisateur

  Scenario: Vérification de la création d'un utilisateur
    Given url 'https://jsonplaceholder.typicode.com/users'
    And request { "name": "John Doe", "email": "john.doe@example.com" }
    When method POST
    Then status 201
    And match response.name == 'John Doe'
    And match response.email == 'john.doe@example.com'
