package com.wallet.crypto.trustapp.service.rpc.entity;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlockbookLatestBlockResponse.kt */
public final class BlockbookLatestBlockResponse {
    private final BackendResponse backend;
    private final BlockbookStateResponse blockbook;

    public BlockbookLatestBlockResponse(BlockbookStateResponse blockbookStateResponse, BackendResponse backendResponse) {
        Intrinsics.checkParameterIsNotNull(blockbookStateResponse, "blockbook");
        Intrinsics.checkParameterIsNotNull(backendResponse, "backend");
        this.blockbook = blockbookStateResponse;
        this.backend = backendResponse;
    }

    public static /* synthetic */ BlockbookLatestBlockResponse copy$default(BlockbookLatestBlockResponse blockbookLatestBlockResponse, BlockbookStateResponse blockbookStateResponse, BackendResponse backendResponse, int i, Object obj) {
        if ((i & 1) != 0) {
            blockbookStateResponse = blockbookLatestBlockResponse.blockbook;
        }
        if ((i & 2) != 0) {
            backendResponse = blockbookLatestBlockResponse.backend;
        }
        return blockbookLatestBlockResponse.copy(blockbookStateResponse, backendResponse);
    }

    public final BlockbookStateResponse component1() {
        return this.blockbook;
    }

    public final BackendResponse component2() {
        return this.backend;
    }

    public final BlockbookLatestBlockResponse copy(BlockbookStateResponse blockbookStateResponse, BackendResponse backendResponse) {
        Intrinsics.checkParameterIsNotNull(blockbookStateResponse, "blockbook");
        Intrinsics.checkParameterIsNotNull(backendResponse, "backend");
        return new BlockbookLatestBlockResponse(blockbookStateResponse, backendResponse);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BlockbookLatestBlockResponse) {
                BlockbookLatestBlockResponse blockbookLatestBlockResponse = (BlockbookLatestBlockResponse) obj;
                if (Intrinsics.areEqual(this.blockbook, blockbookLatestBlockResponse.blockbook) && Intrinsics.areEqual(this.backend, blockbookLatestBlockResponse.backend)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final BackendResponse getBackend() {
        return this.backend;
    }

    public final BlockbookStateResponse getBlockbook() {
        return this.blockbook;
    }

    public int hashCode() {
        BlockbookStateResponse blockbookStateResponse = this.blockbook;
        int i = 0;
        int hashCode = (blockbookStateResponse != null ? blockbookStateResponse.hashCode() : 0) * 31;
        BackendResponse backendResponse = this.backend;
        if (backendResponse != null) {
            i = backendResponse.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BlockbookLatestBlockResponse(blockbook=");
        stringBuilder.append(this.blockbook);
        stringBuilder.append(", backend=");
        stringBuilder.append(this.backend);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
