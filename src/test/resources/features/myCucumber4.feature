Feature: Login

  #(Background: init ) -> para repetir pasos
  Background: init
    Given que tengo acceso a instagram
    When  ingreso mi email: "giljb83@gmail.com"

  #rule: separa validaciones
    Rule: Credenciales validas
  Scenario: Como usuario quiero ingresar un email y password para iniciar sesión

    And   ingreso mi password: "andmin123"
    Then  hago clic en el botón iniciar sesión
    And   muestra la página principal

    Rule: Credenciales invalidas
  Scenario: Como usuario quiero ingresar un email y password para iniciar sesión

    Then  hago clic en el botón iniciar sesión
    And   muestra la página principal

  Scenario: Como usuario quiero ingresar un email y password para iniciar sesión

    And   ingreso mi password: "andmin123"
    And   muestra la página principal