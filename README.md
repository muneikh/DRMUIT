# DRMUIT

A sample application to display rewards for eligible customers based on different channel channel descriptions.

The app has two services.
1. Rewards
2. Eligibility

Rewards: This service returns rewards based on user channel subscriptions.
Eligibility: This service checks if a certain user is applicable for the rewards.

Both of the services are mocked to demonstrate unit and integration tests.

An sample application demonstrating how to use Dagger 2.0, retrofit, mocking services and unit/integration tests.

Features
--------

* The app contains both Unit (Robolectric) and Integration(Espresso) example tests.
* Used Dagger 2.0 and ButterKnife for dependency injection
* Used Java8 and Lambda annotations
* Used Retrofit for network requests
* Used Timber as a logging library

License
-------

    Copyright 2013 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.