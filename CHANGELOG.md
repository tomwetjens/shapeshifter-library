# Changelog

Starting from version 3.0.0, all notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.0.0]

### Added

- Support for authorization when sending UFTP messages. This entails:  
  - the addition of the ParticipantAuthorizationProvider interface that is used to take care of the authorization
  - a stub implementation of ParticipantAuthorizationProvider in the spring-module, which throws an exception when called
  - the extension of UftpParticipantInformation with a property that tells whether authorization is required
  - see README.md for more details


