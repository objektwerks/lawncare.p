Lawncare [ Personal Edition ]
-----------------------------
Lawn care app using ScalaFx, ScalikeJdbc, Ox, H2, HikariCP and Scala 3.

Model
-----
* Account 1 --> * Property
* Property 1 --> * Session
* Property 1 --> * Issue
* Fault

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