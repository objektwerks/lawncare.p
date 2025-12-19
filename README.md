Lawncare [ Personal Edition ]
-----------------------------
Lawn care app using ScalaFx, ScalikeJdbc, Ox, H2, HikariCP and Scala 3.

WARNING
-------
>NPM Security has changed! Here's a forum thread on the debacle:
* [NPM 2FA / OTP Debacle](https://github.com/orgs/community/discussions/181802)

Install
-------
1. Select [Lawncare](https://www.jdeploy.com/~lawncare)
2. Select a platform to download a compressed app installer.
3. Decompress app installer.
4. Install app by double-clicking app installer.
5. Select app icon to launch app.
>This install has been tested on macOS.

Model
-----
* Property 1 --> * Session
* Property 1 --> * Issue

Session
-------
>Session properties:
* mowed ( yard )
* edged ( driveway, curbing )
* trimmed ( plant, tree )
* blowed ( blower, driveway, plant beds )
* fertilized ( nitrogen, phosphorus and potassium )
* pesticided ( herbicides, insecticides, nematicides, fungicides )
* weeded ( plant beds )e
* watered ( sprinkler system, sprinkler heads and pipes )
* repaired ( sprinkler system, pipes, heads, on/off | uncapped/capped )
* note ( anyting pertaining to a yard session )

Location
--------
>The Property.location property can contain the following:
1. [Address to Lat/Lon Converter](https://www.latlong.net/convert-address-to-lat-long.html)
2. [Google Maps](https://support.google.com/maps/answer/18539?hl=en&co=GENIE.Platform=Android)
3. Short Address
4. Long Address
5. Or any special encoded address. :)

Build
-----
1. ```sbt clean compile```

Test
----
1. ```sbt clean test```

Run
---
1. ```sbt run```

Assembly
--------
1. ```sbt clean test assembly copyAssemblyJar```

Execute
-------
1. ```java -jar .assembly/lawncare-$version.jar```

Deploy
------
1. edit build.sbt ( version )
2. edit app.conf ( about > alert > contentText )
3. edit package.json ( version + jdeploy / jar )
4. sbt clean test assembly copyAssemblyJar
5. perform github release ( from https://github.com/objektwerks/lawncare.p )
6. npm login
7. jdeploy publish ( to https://www.jdeploy.com/~lawncare )
8. check email for npm message
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.

jDeploy Install
---------------
1. Setup npm account at npmjs.com
2. Install node, which installs npm, which bundles npx.
3. Install jdeploy via npm - *npm install -g jdeploy*
4. Add icon.png ( 256x256 or 512x512 ) to project root and resources.
5. Edit jDeploy *package.json* as required.
6. Add *jdeploy* and *jdeploy-bundle* to .gitignore
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.

NPM Versioning
--------------
>The ```build.sbt``` **must** contain a ```semver 3-digit``` **version** number. See: [Npmjs Semver](https://docs.npmjs.com/about-semantic-versioning)

NPM Registry
------------
>Lawncare is deployed to: https://www.npmjs.com/package/lawncare

Resources
---------
* [JavaFX](https://openjfx.io/index.html)
* [JavaFX Tutorial](https://jenkov.com/tutorials/javafx/index.html)
* [ScalaFX](http://www.scalafx.org/)

License
-------
>Copyright (c) [2025] [Objektwerks]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.