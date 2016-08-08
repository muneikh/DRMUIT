# DRMUIT

A sample application to display rewards for eligible customers based on different channel channel descriptions.

The app has two services.
1. Rewards
2. Eligibility

Rewards: This service returns rewards based on user channel subscriptions.
Eligibility: This service checks if a certain user is applicable for the rewards.

Both of the services are mocked to demonstrate unit and integration tests.

An sample application demonstrating how to use Dagger 2.0, retrofit, mocking services and unit/integration tests.

- The app contains both Unit (Robolectric) and Integration(Espresso) example tests.
- Used Dagger 2.0 and ButterKnife for dependency injection
- Used Java8 and Lambda annotations
- Used Retrofit for network requests
- Used Timber as a logging library