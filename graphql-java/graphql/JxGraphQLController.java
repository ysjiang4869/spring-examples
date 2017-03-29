package org.opencenter.service.node.service.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.opencenter.service.node.service.node.JiNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiangYueSong on 2017/3/29 0029.
 *
 */
@RestController
@RequestMapping(value = "/graph")
public class JxGraphQLController {
    private final GraphSchema graphSql;
    private GraphQLSchema schema;

    @Autowired
    public JxGraphQLController(GraphSchema graphSql) {
        this.graphSql = graphSql;
        schema=graphSql.getSchema();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object get(@RequestParam(value = "q")String query){
        Object ret=new GraphQL(schema).execute(query).getData();
        return ret;
    }
}
