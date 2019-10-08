(function() {
    var config = {
        address: "%1$s",
        chainId: "%3$s",
        rpcUrl: "%2$s"
    };
    const provider = new window.Trust(config);
    window.ethereum = provider;
    provider.postMessage = function(handler, id, data) {
      switch (handler) {
        case 'signTransaction':
          var gasLimit = data.gasLimit || data.gas || null;
          var gasPrice = data.gasPrice || null;
          var nonce = data.nonce || -1;
          return trust.signTransaction(id, data.to || null, data.value, nonce, gasLimit, gasPrice, data.data || null);
        case 'signMessage':
          return trust.signMessage(id, data.data);
        case 'signPersonalMessage':
          return trust.signPersonalMessage(id, data.data);
        case 'signTypedMessage':
        case 'eth_signTypedData_v3':
          return trust.signTypedMessage(id, JSON.stringify(data.data));
        case 'requestAccounts':
        case 'eth_requestAccounts':
          return trust.requestAccounts(id, "{}");
      }
    };
    window.web3 = new window.Web3(provider);
    window.web3.eth.defaultAccount = config.address;
    window.chrome = {webstore: {}};
})();
