package study.clipart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClipart is a Querydsl query type for Clipart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClipart extends EntityPathBase<Clipart> {

    private static final long serialVersionUID = -1487375927L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClipart clipart = new QClipart("clipart");

    public final QCategory category;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public QClipart(String variable) {
        this(Clipart.class, forVariable(variable), INITS);
    }

    public QClipart(Path<? extends Clipart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClipart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClipart(PathMetadata metadata, PathInits inits) {
        this(Clipart.class, metadata, inits);
    }

    public QClipart(Class<? extends Clipart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

