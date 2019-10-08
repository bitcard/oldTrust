package com.wallet.crypto.trustapp.ui.dapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.DappLink;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.entity.WatchOnlyError;
import com.wallet.crypto.trustapp.interact.UrlHandlerInteract;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.assets.AssetsController;
import com.wallet.crypto.trustapp.repository.network.BlockchainRepository;
import com.wallet.crypto.trustapp.repository.session.SessionRepository;
import com.wallet.crypto.trustapp.router.QRScannerRouter;
import com.wallet.crypto.trustapp.ui.MenuFragment;
import com.wallet.crypto.trustapp.ui.ToolbarActivity;
import com.wallet.crypto.trustapp.ui.dapp.controller.OnSignMessageListener;
import com.wallet.crypto.trustapp.ui.dapp.controller.OnSignTransactionListener;
import com.wallet.crypto.trustapp.ui.dapp.controller.TrustJavaScriptInterface;
import com.wallet.crypto.trustapp.ui.dapp.entity.BrowserUrl;
import com.wallet.crypto.trustapp.ui.dapp.entity.SignMessageError;
import com.wallet.crypto.trustapp.ui.dapp.factory.BrowserViewModelFactory;
import com.wallet.crypto.trustapp.ui.dapp.factory.DuckDuckGoUrlCreator;
import com.wallet.crypto.trustapp.ui.dapp.factory.GoogleSearchUrlCreator;
import com.wallet.crypto.trustapp.ui.dapp.factory.SearchUrlCreator;
import com.wallet.crypto.trustapp.ui.dapp.interact.JsInjectorInteract;
import com.wallet.crypto.trustapp.ui.dapp.network.BrowserClientListener;
import com.wallet.crypto.trustapp.ui.dapp.network.FileChooseListener;
import com.wallet.crypto.trustapp.ui.dapp.network.InterceptBrowserClient;
import com.wallet.crypto.trustapp.ui.dapp.network.TrustChromeClient;
import com.wallet.crypto.trustapp.ui.dapp.view.BrowserBottomPanelLayout;
import com.wallet.crypto.trustapp.ui.dapp.view.BrowserControlsView;
import com.wallet.crypto.trustapp.ui.dapp.view.BrowserView;
import com.wallet.crypto.trustapp.ui.dapp.viewmodel.BrowserViewModel;
import com.wallet.crypto.trustapp.ui.start.activity.MainActivity;
import com.wallet.crypto.trustapp.ui.transfer.fragment.ConfirmTransactionFragment;
import com.wallet.crypto.trustapp.ui.transfer.fragment.ConfirmTransactionFragment.Listener;
import com.wallet.crypto.trustapp.util.CryptoUtils;
import com.wallet.crypto.trustapp.util.EIP712TypedData;
import com.wallet.crypto.trustapp.util.QRUri;
import com.wallet.crypto.trustapp.widget.TWToolbarHelper;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;
import trust.blockchain.Slip;
import trust.blockchain.entity.Message;
import trust.blockchain.entity.TransactionSigned;
import trust.blockchain.entity.TransactionUnsigned;

public class BrowserFragment extends MenuFragment implements FileChooseListener {
    @Inject
    /* renamed from: b */
    DispatchingAndroidInjector<Fragment> f22117b;
    @Inject
    /* renamed from: c */
    JsInjectorInteract f22118c;
    @Inject
    /* renamed from: d */
    protected BrowserViewModelFactory f22119d;
    @Inject
    /* renamed from: e */
    protected SessionRepository f22120e;
    @Inject
    /* renamed from: f */
    protected AssetsController f22121f;
    @Inject
    /* renamed from: g */
    protected BlockchainRepository f22122g;
    @Inject
    /* renamed from: h */
    protected PreferenceRepositoryType f22123h;
    @Inject
    /* renamed from: i */
    protected UrlHandlerInteract f22124i;
    /* renamed from: j */
    private BrowserViewModel f22125j;
    /* renamed from: k */
    private final Handler f22126k = new Handler();
    /* renamed from: l */
    private View f22127l;
    /* renamed from: m */
    private BrowserControlsView f22128m;
    /* renamed from: n */
    private BrowserBottomPanelLayout f22129n;
    /* renamed from: o */
    private BrowserView f22130o;
    /* renamed from: p */
    private SwipeRefreshLayout f22131p;
    /* renamed from: q */
    private InterceptBrowserClient f22132q;
    /* renamed from: r */
    private TrustJavaScriptInterface f22133r;
    /* renamed from: s */
    private QRScannerRouter f22134s = new QRScannerRouter();
    /* renamed from: t */
    private final MutableLiveData<DappLink> f22135t = new MutableLiveData();
    /* renamed from: u */
    private OnSignTransactionListener f22136u = new C24123();
    /* renamed from: v */
    private OnSignMessageListener f22137v = new C24134();
    /* renamed from: w */
    private final Listener f22138w = new C24145();
    /* renamed from: x */
    private final BrowserClientListener f22139x = new C24156();

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment$1 */
    class C24101 implements BrowserControlsView.Listener {
        C24101() {
        }

        public Session getSession() {
            return BrowserFragment.this.f22120e.getSession();
        }

        public void onLoadUrl(String str, Slip slip) {
            BrowserFragment.this.loadUrl(str, slip);
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment$2 */
    class C24112 implements BrowserBottomPanelLayout.Listener {
        C24112() {
        }

        public void onBack() {
            BrowserFragment.this.f22130o.goBack();
        }

        public void onBookmark() {
            BrowserFragment.this.onAddBookmark();
        }

        public void onForward() {
            BrowserFragment.this.f22130o.goForward();
        }

        public void onHome() {
            FragmentActivity activity = BrowserFragment.this.getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).hideBrowser();
            }
        }

        public void onReload() {
            BrowserFragment.this.f22130o.onReload();
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment$3 */
    class C24123 implements OnSignTransactionListener {
        C24123() {
        }

        public void onSignTransaction(TransactionUnsigned transactionUnsigned) {
            BrowserFragment.this.f22125j.prepareToSign(transactionUnsigned);
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment$4 */
    class C24134 implements OnSignMessageListener {
        C24134() {
        }

        public void onSignMessage(Message<String> message) {
            BrowserFragment.this.f22125j.prepareMessage(message);
        }

        public void onSignPersonalMessage(Message<String> message) {
            BrowserFragment.this.f22125j.preparePersonalMessage(message);
        }

        public void onSignTypedMessage(Message<EIP712TypedData> message) {
            BrowserFragment.this.f22125j.prepareTypedMessage(message);
        }

        public void reload() {
            BrowserFragment.this.f22130o.onReload();
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment$5 */
    class C24145 implements Listener {
        C24145() {
        }

        public void onCancel(TransactionUnsigned transactionUnsigned) {
            BrowserFragment.this.cancelFragment(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
            BrowserFragment.this.f22130o.onSignCancel(transactionUnsigned);
        }

        public void onComplete(TransactionSigned transactionSigned) {
            BrowserFragment.this.cancelFragment(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
            BrowserFragment.this.f22130o.onSignSuccessful(transactionSigned);
        }

        public void onError(TransactionUnsigned transactionUnsigned, String str) {
            BrowserFragment.this.cancelFragment(R.anim.fragment_translation_in, R.anim.fragment_translation_out);
            BrowserFragment.this.f22130o.onSignError(transactionUnsigned, str);
        }
    }

    /* renamed from: com.wallet.crypto.trustapp.ui.dapp.fragment.BrowserFragment$6 */
    class C24156 implements BrowserClientListener {
        C24156() {
        }

        public void newPageLoad(String str) {
            BrowserFragment.this.loadUrl(str);
            BrowserFragment.this.f22131p.setRefreshing(true);
            BrowserFragment.this.f22130o.setAlpha(0.6f);
            BrowserFragment.this.updateNavigationButtons();
        }

        public void onLoaded() {
            BrowserFragment.this.f22130o.hideProgress();
            BrowserFragment.this.f22131p.setRefreshing(false);
            BrowserFragment.this.f22130o.setAlpha(1.0f);
            BrowserFragment.this.updateNavigationButtons();
            BrowserFragment.this.hasInBookmarks();
        }

        public void onPageStarted() {
            BrowserFragment.this.f22131p.setRefreshing(true);
            BrowserFragment.this.f22130o.setAlpha(0.6f);
        }

        public void updateHistory(String str) {
            BrowserFragment.this.onAddHistory(str);
            BrowserFragment.this.updateNavigationButtons();
        }

        public void updateUrl(String str) {
            if (BrowserFragment.this.isAdded() && BrowserFragment.this.isVisible()) {
                BrowserFragment.this.f22128m.setAddress(str, BrowserFragment.this.f22128m.getSelectedCoin());
                BrowserFragment.this.updateNavigationButtons();
            }
        }
    }

    private void cancelFragment(int i, int i2) {
        if (isAdded()) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            Fragment findFragmentById = childFragmentManager.findFragmentById(R.id.fragment_container);
            if (findFragmentById != null) {
                FragmentTransaction customAnimations = childFragmentManager.beginTransaction().setCustomAnimations(i, i2);
                customAnimations.remove(findFragmentById);
                customAnimations.commitAllowingStateLoss();
                this.f22130o.setVisibility(View.VISIBLE);
                this.f22128m.setVisibility(View.VISIBLE);
                this.f22130o.webViewRequestFocus();
            }
        }
    }

    private void hasInBookmarks() {
        this.f22125j.hasInBookmarks(this.f22130o.getCurrentLink(), this.f22128m.getSelectedCoin());
    }

    private void loadUrl(String str) {
        Slip selectedCoin = this.f22128m.getSelectedCoin();
        if (selectedCoin == null) {
            selectedCoin = this.f22120e.getSession().wallet.defaultAccount().coin;
        }
        loadUrl(str, selectedCoin);
    }

    private void onAddBookmark() {
        BrowserView browserView = this.f22130o;
        if (browserView != null) {
            String currentLink = browserView.getCurrentLink();
            String title = browserView.getTitle();
            if (!(currentLink == null || title == null)) {
                this.f22125j.addBookmarkLink(currentLink, title, this.f22128m.getSelectedCoin());
            }
        }
    }

    private void onAddHistory(String str) {
        this.f22125j.addHistoryLink(str, this.f22130o.getTitle(), this.f22128m.getSelectedCoin());
    }

    private void onMessagePrepared(Message<String> message) {
        Context context = getContext();
        if (context != null) {
            new Builder(context)
                    .setTitle((int) R.string.browser_signMessage_title)
                    .setMessage(getString(R.string.browser_signMessage_description, CryptoUtils.decodeMessageData(message)))
                    .setPositiveButton((int) R.string.OK, ((dialogInterface, i) -> f22125j.signMessage(f22128m.getSelectedCoin())))
                    .setNegativeButton((int) R.string.Cancel, ((dialogInterface, i) -> f22130o.onSignCancel(message)))
                    .create()
                    .show();
        }
    }

    private void onPersonalMessagePrepared(Message<String> message) {
        Context context = getContext();
        if (context != null) {
            new Builder(context)
                    .setTitle((int) R.string.browser_signMessage_title)
                    .setMessage(getString(R.string.browser_signMessage_description, CryptoUtils.decodeMessageData(message)))
                    .setPositiveButton((int) R.string.OK, (dialogInterface, i) -> f22125j.signPersonalMessage(f22128m.getSelectedCoin()))
                    .setNegativeButton((int) R.string.Cancel, ((dialogInterface, i) -> f22130o.onSignCancel(message)))
                    .create()
                    .show();
        }
    }

    private void onSession(Session session) {
        this.f22128m.setSession(session);
    }

    private void onShareUrl(View view) {
        String uri = Uri.parse("https://link.trustwallet.com/openUrl/").buildUpon().appendQueryParameter("link", this.f22130o.getCurrentLink()).build().toString();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.shareUrl_intent_subjectDescription));
        intent.putExtra("android.intent.extra.TEXT", uri);
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    private void onTransactionPrepared(TransactionUnsigned transactionUnsigned) {
        this.f22126k.postDelayed(() -> f22130o.setVisibility(View.INVISIBLE), 300);
        this.f22127l.setVisibility(View.VISIBLE);
        getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_translation_in, R.anim.fragment_translation_out).replace(R.id.fragment_container, ConfirmTransactionFragment.create(transactionUnsigned, this.f22138w)).commit();
        if (((ToolbarActivity) getActivity()) != null) {
            this.f22128m.setVisibility(View.GONE);
        }
    }

    private void onTypedMessagePrepared(Message<EIP712TypedData> message) {
        Context context = getContext();
        if (context != null) {
            new Builder(context)
                    .setTitle((int) R.string.browser_signMessage_title)
                    .setMessage(getString(R.string.browser_signMessage_description, ((EIP712TypedData) message.value).toString()))
                    .setPositiveButton((int) R.string.OK, ((dialogInterface, i) -> f22125j.signTypedMessage(f22128m.getSelectedCoin())))
                    .setNegativeButton((int) R.string.Cancel, ((dialogInterface, i) -> f22130o.onSignCancel(message)))
                    .create().show();
        }
    }

    private void showError(String str, String str2) {
        Context context = getContext();
        if (context != null) {
            new Builder(context).setTitle((CharSequence) str).setMessage((CharSequence) str2).setPositiveButton((int) R.string.OK, null).create().show();
        }
    }

    private void updateNavigationButtons() {
        this.f22129n.setBackEnabled(this.f22130o.canGoBack());
        this.f22129n.setForwardEnabled(this.f22130o.canGoForward());
        this.f22129n.setHasBookmark(false);
    }

    public void clearCache() {
        this.f22130o.clearCache();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        BrowserView browserView = this.f22130o;
        browserView.setChromeClient(new TrustChromeClient(browserView, this));
        this.f22133r = new TrustJavaScriptInterface(this.f22130o, this.f22120e, this.f22136u, this.f22137v);
        this.f22130o.setJsInterface(this.f22133r);
        this.f22132q = new InterceptBrowserClient(this.f22118c, this.f22139x, this.f22124i);
        this.f22130o.setWebViewClient(this.f22132q);
        this.f22127l.setVisibility(View.INVISIBLE);
        this.f22125j = (BrowserViewModel) ViewModelProviders.of((Fragment) this, this.f22119d).get(BrowserViewModel.class);
        this.f22125j.transactionUnsigned().observe(this, transactionUnsigned -> onTransactionPrepared(transactionUnsigned));
        this.f22125j.message().observe(this, stringMessage -> onMessagePrepared(stringMessage));
        this.f22125j.personalMessage().observe(this, stringMessage -> onPersonalMessagePrepared(stringMessage));
        this.f22125j.typedMessage().observe(this, eip712TypedDataMessage -> onTypedMessagePrepared(eip712TypedDataMessage));
        LiveData signedMessage = this.f22125j.signedMessage();
        BrowserView browserView2 = this.f22130o;
        browserView2.getClass();
        signedMessage.observe(this, new C2426s(browserView2));
        signedMessage = this.f22125j.signedPersonalMessage();
        browserView2 = this.f22130o;
        browserView2.getClass();
        signedMessage.observe(this, new C2423p(browserView2));
        signedMessage = this.f22125j.signedTypedMessage();
        browserView2 = this.f22130o;
        browserView2.getClass();
        signedMessage.observe(this, new C2424q(browserView2));
        this.f22125j.bookmark().observe(this, new C2416a(this));
        this.f22125j.getError().observe(this, new C2425r(this));
        this.f22125j.getSession().observe(this, session -> onSession(session));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1023) {
            Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.fragment_container);
            if (findFragmentById != null) {
                findFragmentById.onActivityResult(i, i2, intent);
            }
            return;
        }
        QRUri onActivityResult = this.f22134s.onActivityResult(i, i2, intent);
        if (onActivityResult == null) {
            super.onActivityResult(i, i2, intent);
        } else {
            loadUrl(onActivityResult.getUri());
        }
    }

    public boolean onBack() {
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById != null && findFragmentById.isVisible()) {
            if (findFragmentById instanceof ConfirmTransactionFragment) {
                ConfirmTransactionFragment confirmTransactionFragment = (ConfirmTransactionFragment) findFragmentById;
                if (confirmTransactionFragment.onBackPress()) {
                    this.f22138w.onCancel(confirmTransactionFragment.getTransactionUnsigned());
                }
            } else {
                cancelFragment(17432576, 17432577);
            }
            return true;
        } else if (!this.f22130o.canGoBack()) {
            return false;
        } else {
            this.f22130o.goBack();
            return true;
        }
    }

    public void onCreate(Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_web_browser, viewGroup, false);
    }

    public void onError(Throwable th) {
        String string;
        String string2;
        if (th instanceof SignMessageError) {
            Message data = ((SignMessageError) th).getData();
            string = getString(R.string.browser_failedToSignMessage_message);
            string2 = getString(R.string.browser_watchOnly_title, string);
            this.f22130o.onSignCancel(data);
        } else if (th instanceof WatchOnlyError) {
            string2 = getString(R.string.browser_watchOnly_title);
            string = getString(R.string.InCoordinatorError_onlyWatchAccount);
        } else {
            string2 = getString(R.string.transaction_cell_error_title);
            string = getString(R.string.browser_failedToSignMessage_message);
        }
        showError(string2, string);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBack();
        return true;
    }

    public void onPause() {
        super.onPause();
        this.f22130o.onPause();
        this.f22132q.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f22130o.onResume();
        this.f22132q.onResume();
    }

    public boolean onShowFileChooser(ValueCallback<Uri[]> valueCallback) {
        FragmentActivity activity = getActivity();
        return activity instanceof FileChooseListener ? ((FileChooseListener) activity).onShowFileChooser(valueCallback) : false;
    }

    public void onUpdateMenu(TWToolbarHelper tWToolbarHelper, Menu menu, MenuInflater menuInflater) {
        tWToolbarHelper.addToolbarView(this.f22128m, -1, 0);
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.fragment_container);
        int i = (findFragmentById != null && findFragmentById.isAdded() && findFragmentById.isVisible()) ? 1 : 0;
        if (i == 0) {
            disableDisplayHomeAsUp();
            this.f22128m.setVisibility(View.VISIBLE);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22127l = view.findViewById(R.id.fragment_container);
        this.f22130o = (BrowserView) view.findViewById(R.id.web_view_container);
        this.f22131p = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        this.f22128m = new BrowserControlsView(view.getContext(), new C24101());
        this.f22128m.setOnShareUrlClickListener(view1 -> onShareUrl(view1));
        this.f22129n = (BrowserBottomPanelLayout) view.findViewById(R.id.bottom_controls);
        this.f22129n.setListener(new C24112());
        this.f22131p.setEnabled(false);
        this.f22135t.observe(this, new C2419f(this));
    }

    public void openPage(DappLink dappLink) {
        this.f22135t.postValue(dappLink);
    }

    private void loadUrl(String str, Slip slip) {
        SearchUrlCreator duckDuckGoUrlCreator;
        if (this.f22120e.getSession().wallet.account(slip) == null) {
            slip = this.f22120e.getSession().wallet.defaultAccount().coin;
        }
        this.f22118c.setCoin(slip);
        this.f22133r.setCoin(slip);
        if ("DuckDuckGo".equalsIgnoreCase(this.f22123h.getSearchEngine())) {
            duckDuckGoUrlCreator = new DuckDuckGoUrlCreator();
        } else {
            duckDuckGoUrlCreator = new GoogleSearchUrlCreator();
        }
        BrowserUrl browserUrl = new BrowserUrl(str, duckDuckGoUrlCreator);
        this.f22128m.setAddress(browserUrl.toString(), slip);
        this.f22130o.loadUrl(browserUrl.toString());
    }

    public static void m274a(BrowserFragment r3, DappLink r4) {
        r3.f22129n.setHasBookmark(r4 != null && r4.url.equals(r3.f22130o.getCurrentLink()) && r4.coin == r3.f22128m.getSelectedCoin());
    }

    public static void m280b(BrowserFragment r1, DappLink r2) {
        if (r2 != null) {
            r1.loadUrl(r2.url, r2.coin);
            r1.f22135t.postValue(null);
        }
    }
}
