package org.opencenter.service.node.service.graphql;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import org.opencenter.service.node.service.JxAppService;
import org.opencenter.service.node.service.node.JxNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by JiangYueSong on 2017/3/29 0029.
 *
 */
@Service
public class GraphSchema {

    private GraphQLOutputType nodeType;
    private GraphQLSchema schema;
    private final JxAppService svc;
    @Autowired
    public GraphSchema(JxAppService svc) {
        initOutputType();
        this.svc = svc;
        schema=GraphQLSchema.newSchema().
                query(newObject().
                        name("GraphQuery").
                        field(get()).
                        build())
                .build();
    }

    private void initOutputType() {
        /**
         * node对象结构
         */
        nodeType = newObject()
                .name("User")
                .field(newFieldDefinition().name("uuid").type(GraphQLString).build())
                .field(newFieldDefinition().name("id").type(GraphQLString).build())
                .field(newFieldDefinition().name("level").type(GraphQLInt).build())
                .field(newFieldDefinition().name("crtdate").type(GraphQLString).build())
                .field(newFieldDefinition().name("upddate").type(GraphQLString).build())
                .build();
    }

    private GraphQLFieldDefinition get(){
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("node")
                .argument(newArgument().name("id").type(GraphQLString).build())
                .type(nodeType)
                .dataFetcher(environment ->{
                    String id = environment.getArgument("id");
                    JxNode node=(JxNode)svc.getNodeService().get(id);
                    JxTestNode ret=new JxTestNode();
                    ret.setId(node.getId());
                    ret.setUuid(node.getUuid().toString());
                    ret.setLevel(node.getLevel());
                    ret.setCrtdate(node.getCrtdate().toString());
                    ret.setUpddate(node.getUpddate().toString());
                    return ret;
                })
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

    public void setSchema(GraphQLSchema schema) {
        this.schema = schema;
    }
}
