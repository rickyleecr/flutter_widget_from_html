import 'package:webview_flutter/webview_flutter.dart';

abstract class WebViewSource {
  String get data;
  Future<void> loadWith(WebViewController controller);
}

/// The website URL.
class URLWebViewSource implements WebViewSource {
  final String url;
  const URLWebViewSource(this.url);

  @override
  String get data => url;

  @override
  Future<void> loadWith(WebViewController controller) async {
    final uri = Uri.tryParse(url);
    if (uri != null) {
      await controller.loadRequest(uri);
    }
  }
}

/// The raw HTML content of the webpage.
class HTMLWebViewSource implements WebViewSource {
  final String html;
  const HTMLWebViewSource(this.html);

  @override
  String get data => html;

  @override
  Future<void> loadWith(WebViewController controller) async {
    await controller.loadHtmlString(html);
  }
}
