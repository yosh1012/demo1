# demo1

## Environment

### Tech stack

#### backend

- Scala (JVM) with Bazel
  - reducing build time and make debugging easier
  - using pekko framework to learn actormodel, and for good performance and availability
    
#### frontend

- React with Typescript
  - debugging with vite
  - using Bazel to build a distribution file
- tailwind (for modern styling practice)

#### DB

- PostgreSQL (for scalability)
- Redis (in-memory, for real-time concurrent editing feature)

### env confirmation

```
yoshi@DESKTOP-88R3T37:~/demo1$ bazel --version

bazel 8.4.2

yoshi@DESKTOP-88R3T37:~/demo1$ java -version
openjdk version "21.0.8" 2025-07-15
OpenJDK Runtime Environment (build 21.0.8+9-Ubuntu-0ubuntu124.04.1)
OpenJDK 64-Bit Server VM (build 21.0.8+9-Ubuntu-0ubuntu124.04.1, mixed mode, sharing)

yoshi@DESKTOP-88R3T37:~/demo1$ psql --version
psql (PostgreSQL) 16.11 (Ubuntu 16.11-0ubuntu0.24.04.1)

yoshi@DESKTOP-88R3T37:~/demo1$ node --version
v20.18.2

yoshi@DESKTOP-88R3T37:~/demo1$ npm --version
10.8.2
```

## dependencies

### checking bazel dependencies

```

yoshi@DESKTOP-88R3T37:~/demo1$ bazel query @maven_demo1//...
@maven_demo1//:ch_qos_logback_logback_classic
@maven_demo1//:ch_qos_logback_logback_classic_1_4_14
@maven_demo1//:ch_qos_logback_logback_core
@maven_demo1//:ch_qos_logback_logback_core_1_4_14
@maven_demo1//:com_fasterxml_jackson_core_jackson_annotations
@maven_demo1//:com_fasterxml_jackson_core_jackson_annotations_2_15_2
@maven_demo1//:com_fasterxml_jackson_core_jackson_core
@maven_demo1//:com_fasterxml_jackson_core_jackson_core_2_15_2
@maven_demo1//:com_fasterxml_jackson_core_jackson_databind
@maven_demo1//:com_fasterxml_jackson_core_jackson_databind_2_15_2
@maven_demo1//:com_fasterxml_jackson_datatype_jackson_datatype_jdk8
@maven_demo1//:com_fasterxml_jackson_datatype_jackson_datatype_jdk8_2_14_3
@maven_demo1//:com_fasterxml_jackson_datatype_jackson_datatype_jsr310
@maven_demo1//:com_fasterxml_jackson_datatype_jackson_datatype_jsr310_2_14_3
@maven_demo1//:com_github_jwt_scala_jwt_core_2_13
@maven_demo1//:com_github_jwt_scala_jwt_core_2_13_9_4_5
@maven_demo1//:com_github_pjfanning_pekko_http_play_json_2_13
@maven_demo1//:com_github_pjfanning_pekko_http_play_json_2_13_2_1_0
@maven_demo1//:com_typesafe_config
@maven_demo1//:com_typesafe_config_1_4_3
@maven_demo1//:com_typesafe_play_play_functional_2_13
@maven_demo1//:com_typesafe_play_play_functional_2_13_2_10_4
@maven_demo1//:com_typesafe_play_play_json_2_13
@maven_demo1//:com_typesafe_play_play_json_2_13_2_10_4
@maven_demo1//:com_typesafe_scala_logging_scala_logging_2_13
@maven_demo1//:com_typesafe_scala_logging_scala_logging_2_13_3_9_5
@maven_demo1//:com_typesafe_slick_slick_2_13
@maven_demo1//:com_typesafe_slick_slick_2_13_3_5_0
@maven_demo1//:com_typesafe_slick_slick_hikaricp_2_13
@maven_demo1//:com_typesafe_slick_slick_hikaricp_2_13_3_5_0
@maven_demo1//:com_typesafe_ssl_config_core_2_13
@maven_demo1//:com_typesafe_ssl_config_core_2_13_0_6_1
@maven_demo1//:com_zaxxer_HikariCP
@maven_demo1//:com_zaxxer_HikariCP_4_0_3
@maven_demo1//:defs
@maven_demo1//:io_lettuce_lettuce_core
@maven_demo1//:io_lettuce_lettuce_core_6_3_1_RELEASE
@maven_demo1//:io_netty_netty_buffer
@maven_demo1//:io_netty_netty_buffer_4_1_104_Final
@maven_demo1//:io_netty_netty_codec
@maven_demo1//:io_netty_netty_codec_4_1_104_Final
@maven_demo1//:io_netty_netty_common
@maven_demo1//:io_netty_netty_common_4_1_104_Final
@maven_demo1//:io_netty_netty_handler
@maven_demo1//:io_netty_netty_handler_4_1_104_Final
@maven_demo1//:io_netty_netty_resolver
@maven_demo1//:io_netty_netty_resolver_4_1_104_Final
@maven_demo1//:io_netty_netty_transport
@maven_demo1//:io_netty_netty_transport_4_1_104_Final
@maven_demo1//:io_netty_netty_transport_native_unix_common
@maven_demo1//:io_netty_netty_transport_native_unix_common_4_1_104_Final
@maven_demo1//:io_projectreactor_reactor_core
@maven_demo1//:io_projectreactor_reactor_core_3_6_2
@maven_demo1//:org_apache_pekko_pekko_actor_2_13
@maven_demo1//:org_apache_pekko_pekko_actor_2_13_1_0_3
@maven_demo1//:org_apache_pekko_pekko_actor_testkit_typed_2_13
@maven_demo1//:org_apache_pekko_pekko_actor_testkit_typed_2_13_1_0_3
@maven_demo1//:org_apache_pekko_pekko_actor_typed_2_13
@maven_demo1//:org_apache_pekko_pekko_actor_typed_2_13_1_0_3
@maven_demo1//:org_apache_pekko_pekko_http_2_13
@maven_demo1//:org_apache_pekko_pekko_http_2_13_1_0_1
@maven_demo1//:org_apache_pekko_pekko_http_core_2_13
@maven_demo1//:org_apache_pekko_pekko_http_core_2_13_1_0_1
@maven_demo1//:org_apache_pekko_pekko_http_testkit_2_13
@maven_demo1//:org_apache_pekko_pekko_http_testkit_2_13_1_0_1
@maven_demo1//:org_apache_pekko_pekko_parsing_2_13
@maven_demo1//:org_apache_pekko_pekko_parsing_2_13_1_0_1
@maven_demo1//:org_apache_pekko_pekko_protobuf_v3_2_13
@maven_demo1//:org_apache_pekko_pekko_protobuf_v3_2_13_1_0_3
@maven_demo1//:org_apache_pekko_pekko_slf4j_2_13
@maven_demo1//:org_apache_pekko_pekko_slf4j_2_13_1_0_3
@maven_demo1//:org_apache_pekko_pekko_stream_2_13
@maven_demo1//:org_apache_pekko_pekko_stream_2_13_1_0_3
@maven_demo1//:org_apache_pekko_pekko_testkit_2_13
@maven_demo1//:org_apache_pekko_pekko_testkit_2_13_1_0_3
@maven_demo1//:org_checkerframework_checker_qual
@maven_demo1//:org_checkerframework_checker_qual_3_41_0
@maven_demo1//:org_hashids_hashids
@maven_demo1//:org_hashids_hashids_1_0_3
@maven_demo1//:org_mindrot_jbcrypt
@maven_demo1//:org_mindrot_jbcrypt_0_4
@maven_demo1//:org_parboiled_parboiled_2_13
@maven_demo1//:org_parboiled_parboiled_2_13_2_5_0
@maven_demo1//:org_postgresql_postgresql
@maven_demo1//:org_postgresql_postgresql_42_7_1
@maven_demo1//:org_reactivestreams_reactive_streams
@maven_demo1//:org_reactivestreams_reactive_streams_1_0_4
@maven_demo1//:org_scala_lang_modules_scala_xml_2_13
@maven_demo1//:org_scala_lang_modules_scala_xml_2_13_2_1_0
@maven_demo1//:org_scala_lang_scala_library
@maven_demo1//:org_scala_lang_scala_library_2_13_13
@maven_demo1//:org_scala_lang_scala_reflect
@maven_demo1//:org_scala_lang_scala_reflect_2_13_12
@maven_demo1//:org_scalactic_scalactic_2_13
@maven_demo1//:org_scalactic_scalactic_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_2_13
@maven_demo1//:org_scalatest_scalatest_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_compatible
@maven_demo1//:org_scalatest_scalatest_compatible_3_2_17
@maven_demo1//:org_scalatest_scalatest_core_2_13
@maven_demo1//:org_scalatest_scalatest_core_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_diagrams_2_13
@maven_demo1//:org_scalatest_scalatest_diagrams_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_featurespec_2_13
@maven_demo1//:org_scalatest_scalatest_featurespec_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_flatspec_2_13
@maven_demo1//:org_scalatest_scalatest_flatspec_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_freespec_2_13
@maven_demo1//:org_scalatest_scalatest_freespec_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_funspec_2_13
@maven_demo1//:org_scalatest_scalatest_funspec_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_funsuite_2_13
@maven_demo1//:org_scalatest_scalatest_funsuite_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_matchers_core_2_13
@maven_demo1//:org_scalatest_scalatest_matchers_core_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_mustmatchers_2_13
@maven_demo1//:org_scalatest_scalatest_mustmatchers_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_propspec_2_13
@maven_demo1//:org_scalatest_scalatest_propspec_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_refspec_2_13
@maven_demo1//:org_scalatest_scalatest_refspec_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_shouldmatchers_2_13
@maven_demo1//:org_scalatest_scalatest_shouldmatchers_2_13_3_2_17
@maven_demo1//:org_scalatest_scalatest_wordspec_2_13
@maven_demo1//:org_scalatest_scalatest_wordspec_2_13_3_2_17
@maven_demo1//:org_slf4j_slf4j_api
@maven_demo1//:org_slf4j_slf4j_api_2_0_12
@maven_demo1//:outdated
@maven_demo1//:pin
yoshi@DESKTOP-88R3T37:~/demo1$ 
```

## deployment

plan to deploy on fly.io at first
