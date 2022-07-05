
**_此代码使用的是liteFlow-2.8.0版本_**<br><br>
具体文档跳转地址:---[liteFlow官方文档](https://liteflow.yomahub.com/pages/5816c5/#%E5%89%8D%E8%A8%80)
<br><br>项目代码执行流程图---[流程图](https://normalxwx.oss-cn-hangzhou.aliyuncs.com/%E8%B4%AD%E7%89%A9%E6%B5%81%E7%A8%8B%E5%9B%BE.png)
# LiteFlow不适用于哪些场景
>LiteFlow*只做基于逻辑的流转*，而不做基于角色任务的流转;
类似于审批流，A审批完应该是B审批，然后再流转到C角色,是不支持的

# LiteFlow适用于哪些场景
>LiteFlow适用于拥有复杂逻辑的业务，比如说价格引擎，下单流程等，这些业务往往都拥有很多步骤，
这些步骤完全可以按照业务粒度拆分成一个个独立的组件，进行装配复用变更。使用LiteFlow，你会得
到一个灵活度高，扩展性很强的系统。因为组件之间相互独立，也也可以避免改一处而动全身的这样的风险。

#LiteFlow的设计原则
>LiteFlow是基于工作台模式进行设计的，何谓工作台模式？

n个工人按照一定顺序围着一张工作台，按顺序各自生产零件，生产的零件最终能组装成一个机器，
每个工人只需要完成自己手中零件的生产，而无需知道其他工人生产的内容。每一个工人生产所需
要的资源都从工作台上拿取，如果工作台上有生产所必须的资源，则就进行生产，若是没有，就等
到有这个资源。每个工人所做好的零件，也都放在工作台上。

>这个模式有几个好处：

每个工人无需和其他工人进行沟通。工人只需要关心自己的工作内容和工作台上的资源。这样就做到
了每个工人之间的解耦和无差异性。
即便是工人之间调换位置，工人的工作内容和关心的资源没有任何变化。这样就保证了每个工人的稳定性。
如果是指派某个工人去其他的工作台，工人的工作内容和需要的资源依旧没有任何变化，这样就做到了工人的可复用性。
因为每个工人不需要和其他工人沟通，所以可以在生产任务进行时进行实时工位更改：替换，插入，撤掉一些工人，这样生产任务也能实时的被更改。这样就保证了整个生产任务的灵活性。
这个模式映射到LiteFlow框架里，工人就是组件，工人坐的顺序就是流程配置，工作台就是上下文，资源就是参数，最终组装的这个机器就是这个业务。正因为有这些特性，所以LiteFlow能做到统一解耦的组件和灵活的装配。