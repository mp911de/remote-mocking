Remote Mocking Framework for Integration-Tests
=============

This is an example for a remote mocking framework. It's intended to be used as remote mocks for your integration tests.

The framework provides you to define static and dynamic mock responses in order to let your mocks behave correctly.
Static responses are stored on disk (ideally within your SVN/Git/Hg/... repository). Dynamic responses are held within a
Cache. You can use following implementations for a cache:

 * EhCache
 * JBoss Tree-Cache
 * JBoss Infinispan

The configuration has to be done within `remote-mocking-config.xml` (must be available as resource withing the EJB module).

Steps for your mocks
-------------

1. Fork or clone this repo
2. Adapt the config to your needs
3. Write mocks and write your integration tests
4. Deploy and run your integration-test
5. Enjoy freedom

How to mock
-------------
Well, local mocking is quite easy. Remote mocking as well, but you have to be aware of the fact, that you're probably not
the only process which tries to access the mocks. Nevertheless the steps are as following:

1. Create a skeleton of your remote interface class (EJB, Web-Service, REST Resource, ...) which complies to the API contract
2. Add the following two lines of code (more or less):
`MockInvocationRecorder.recordInvocation("AwsItemSearchWs/itemSearch/" + awsAccessKeyId, marketplaceDomain, awsAccessKeyId, associateTag, xmlEscaping, validate, shared, request);
return MockResponseFactory.getResponse("AwsItemSearchWs/itemSearch/" + awsAccessKeyId, AwsItemSearchResponse.class);`
3. That's it.

Invocation key principle
-------------
Every invocation to a mock has to be identified somehow. Due to the API contract of your API you're not allowed to change your API, right?
So you have to identify somehow your invocation without changing the API. It's possible in 99,5% of the cases.
Simply pick a combination of Class-Name, Method-Name and a leading key (e.g. if you want to retrieve a customer you'd pick the customerId). It's sometimes
hard to pick a useful value. The invocation key is hierarchical within the framework: AwsItemSearchWs/itemSearch/myKey

**Please note: Invocation keys are used lower-case in the back-end (file-store, caches)**

Mock Management API
-------------
The framework provides you an API (SOAP and REST) to setup dynamic mock answers, to query for a specific invocation key and a bit more (see MockManagement).
This is necessary in order to have some flexibility on integration testing. You can't always provide static responses. Sometimes you want to supply dynamic ones.

Mock Response (backend format)
-------------
The responses are stored somehow. This somehow is XML. I provided a simple form (containing a value and a possibility to throw exceptions in a generic way).
You're in charge to extend this by your needs. From my experience, as soon as you start using SOAP Services (JAXB-based) it's very handy to create a Root XML class and enclose the response.

Dynamic responses must be stored under the as-is invocation key (within the cache). Static responses are stored within a response directory (see config) and having an `.xml` suffix,  AwsItemSearchWs/itemSearch/myKey.xml then.

Mock Response selection
-------------
Earlier I was talking about a hierarchical invocation key format. This is indeed to be able to do also hierarchical response selection.
Let's take `AwsItemSearchWs/itemSearch/myKey` for example. First, `AwsItemSearchWs/itemSearch/myKey` is looked up (`AwsItemSearchWs/itemSearch/myKey.xml` when looking for a file), then
`AwsItemSearchWs/itemSearch` (`AwsItemSearchWs/itemSearch.xml` when looking for a file) and finally `AwsItemSearchWs`. The depth of this structure is unlimited.

Build and Deployment
-------------
This project is maven based. So just `mvn clean install` for building the project. Before you can start,
you've to create your own `remote-mocking-config.xml` config file! Afterwards drop the EAR into your JBoss.
You can use either JBoss 7 or JBoss 4/5/6 (not tested in this scenario).

