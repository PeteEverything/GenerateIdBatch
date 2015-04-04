# GenerateIdBatch

Problem Statement:

We want to persist requests against an API for deferred processing. There are ~1,000 request/second against the API during peak times. Each request should have a unique identifier that can be persisted.
We want to be able to work on large collections (30-50 million) of these identifiers in memory (assume 4-8GB memory for the entire application).
We want to create large batches of identifiers when processing of requests.
We want to be able to create these identifiers on distributed nodes without requiring communication between nodes (clashes can be possible but should be unlikely).
We want to be able to apply a total order to entities that were created across nodes for later auditing.

Usage:

Set up the config.properties file as per your requirements for batch size, threads, etc.
Running GenerateIds will create an instance of ConsumerThread, or write your own externally.  ConsumerThread is an example, which receives the generated batch of Ids as a Striing Array.
