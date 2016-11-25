# Purpose of this document

This document contains the implementation roadmap for the Parsec State channel. It's organised in _phases_, _milestones_ and _tasks_.

- Phases are major stages in the development lifecycle. They correspond to the first digit of the versioning number. _Alpha_ and _Beta_ phases correspond to major version number __0__. Semantic versioning is not applied in _alpha_ or _beta_. Version numbers __1__ and above correspond to production ready phases and semantic versioning shall apply.

- Milestones have to be implemented sequentially. It's not generally intended to start implementing tasks of a milestone if the previous milestone hasn't been completed. Milestones correspond to the second digit in the version number.

- Tasks can be implemented in parallel and should correspond to independent branches in the code. Each task shall typically end in a Pull Request (PR) before being merged into the master branch. Tasks correspond to the third versioning digit.

# Proof of Concept phase

## Milestone 1 - Replicated payment transaction (v0.1.x)

In this milestone, we will perform a replicated payment transaction between two parties. It will consist in the following steps:

- The payment receiver will create a message containing the 

### Task 00.01.01 - Setting up a test environment
A _Docker Compose_ test infrastructure containing:

- Monolithic Parsec node docker container
