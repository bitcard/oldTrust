package trust.blockchain

import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import trust.blockchain.blockchain.bitcoin.entity.UnspentOutput
import trust.blockchain.util.ExtensionsKt

/* compiled from: UnspentOutputsSelector.kt */
class UnspentOutputsSelector(private val byteFee: Long) {

    fun calcTransactionSize(i: Int, i2: Int = 2): Long {
        return (i * INPUT_SIZE + i2 * 34 + 10).toLong() * 1
    }

    fun estimateFee(i: Int, i2: Int = 2): Long {
        return estimateFee(calcTransactionSize(i, i2))
    }

    fun select(utxos: Array<UnspentOutput>, j: Long): Array<UnspentOutput> {
        Intrinsics.checkParameterIsNotNull(utxos, "utxos")
        val j3 = 2.toLong() * j
        val sortedWith = utxos.sortedWith(Comparator { t1, t2 ->
            compareValues(java.lang.Long.valueOf(java.lang.Long.parseLong(t1.value)), java.lang.Long.valueOf(java.lang.Long.parseLong(t2.value)))
        })

        for (i in 1 until sortedWith.size) {
            val estimateFee = estimateFee(i, 2) + j + dustThreshold

            val arrayList = ArrayList<List<UnspentOutput>>()
            for (next in ExtensionsKt.eachSlices(sortedWith, i)) {
                if (ExtensionsKt.sumValue(next) >= estimateFee) {
                    arrayList.add(next)
                }
            }

            arrayList.sortedWith(comparator = Comparator { t1, t2 ->
                compareValuesBy(ExtensionsKt.sumValue(t1), ExtensionsKt.sumValue(t2), selector = {
                    Math.abs(it - j3)
                })
            })

            if (!arrayList.isEmpty()) {
                return arrayList.first().toTypedArray()
            }
        }

        for (i2 in 1 until sortedWith.size) {
            val estimateFee2 = estimateFee(i2, 2) + j
            val arrayList2 = ArrayList<List<UnspentOutput>>()
            for (next2 in ExtensionsKt.eachSlices(sortedWith, i2)) {
                if ((if (ExtensionsKt.sumValue(next2) >= estimateFee2) 1 else 0) != 0) {
                    arrayList2.add(next2)
                }
            }
            if (arrayList2.isEmpty()) {
                return arrayList2.first().toTypedArray();
            }
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append("Insufficient Funds For Unspent Selection: Target= ")
        stringBuilder.append(j)
        stringBuilder.append(" | Estimated Fee= ")
        stringBuilder.append(estimateFee(utxos.size))
        stringBuilder.append(" | Unspents Sum= ")
        stringBuilder.append(ExtensionsKt.sumValue(utxos.toList()))
        throw SelectorException(stringBuilder.toString())
    }

    fun estimateFee(j: Long): Long {
        return j * this.byteFee
    }

    companion object {
        val INPUT_SIZE = 148
        val OUTPUT_SIZE = 34
        private val dustThreshold: Long = 546
    }
}
