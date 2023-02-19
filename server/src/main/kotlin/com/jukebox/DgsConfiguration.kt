package com.movingimage.lspro.consumer

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsRuntimeWiring
import graphql.scalars.ExtendedScalars
import graphql.scalars.ExtendedScalars.*
import graphql.schema.idl.RuntimeWiring

@DgsComponent
class DgsConfiguration {

    @DgsRuntimeWiring
    fun addScalar(builder: RuntimeWiring.Builder): RuntimeWiring.Builder = builder
        .scalar(DateTime)
        .scalar(Url)
        .scalar(GraphQLLong)

}