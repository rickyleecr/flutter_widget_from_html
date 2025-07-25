# Flutter Widget from HTML (core)

[![Flutter](https://github.com/daohoangson/flutter_widget_from_html/actions/workflows/flutter.yml/badge.svg)](https://github.com/daohoangson/flutter_widget_from_html/actions/workflows/flutter.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=daohoangson_flutter_widget_from_html&metric=coverage)](https://sonarcloud.io/summary/new_code?id=daohoangson_flutter_widget_from_html)
[![Pub](https://img.shields.io/pub/v/flutter_widget_from_html_core.svg)](https://pub.dev/packages/flutter_widget_from_html_core)

Flutter package to render html as widgets that focuses on correctness and extensibility.
Supports [70+ most popular tags](https://demo.fwfh.dev/supported/tags.html).

| [Live demo](https://demo.fwfh.dev/#/helloworldcore)                                                                                 |                                                                                                                                     |
| ----------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| ![](https://raw.githubusercontent.com/daohoangson/flutter_widget_from_html/0001010/packages/core/example/HelloWorldCoreScreen1.gif) | ![](https://raw.githubusercontent.com/daohoangson/flutter_widget_from_html/0001010/packages/core/example/HelloWorldCoreScreen2.jpg) |

## Getting Started

Add this to your app's `pubspec.yaml` file:

```yaml
dependencies:
  flutter_widget_from_html_core: ^0.17.0
```

## Usage

Then you have to import the package with:

```dart
import 'package:flutter_widget_from_html_core/flutter_widget_from_html_core.dart';
```

And use `HtmlWidget` where appropriate:

```dart
HtmlWidget(
  // the first parameter (`html`) is required
  '''
  <h3>Heading</h3>
  <p>
    A paragraph with <strong>strong</strong>, <em>emphasized</em>
    and <span style="color: red">colored</span> text.
  </p>
  ''',

  // all other parameters are optional, a few notable params:

  // specify custom styling for an element
  // see supported inline styling below
  customStylesBuilder: (element) {
    if (element.classes.contains('foo')) {
      return {'color': 'red'};
    }

    return null;
  },

  customWidgetBuilder: (element) {
    if (element.attributes['foo'] == 'bar') {
      // render a custom widget that takes the full width
      return FooBarWidget();
    }

    if (element.attributes['fizz'] == 'buzz') {
      // render a custom widget that inlines with surrounding text
      return InlineCustomWidget(
        child: FizzBuzzWidget(),
      )
    }

    return null;
  },

  // this callback will be triggered when user taps a link
  onTapUrl: (url) => print('tapped $url'),

  // select the render mode for HTML body
  // by default, a simple `Column` is rendered
  // consider using `ListView` or `SliverList` for better performance
  renderMode: RenderMode.column,

  // set the default styling for text
  textStyle: TextStyle(fontSize: 14),
),
```

## Features

### HTML tags

Below tags are the ones that have special meaning / styling, all other tags will be parsed as text.
[Compare between Flutter rendering and browser's.](https://demo.fwfh.dev/supported/tags.html)

- A: underline, theme accent color with scroll to anchor support
- H1/H2/H3/H4/H5/H6
- IMG with support for asset (`asset://`), data uri, local file (`file://`) and network image
- LI/OL/UL with support for:
  - Attributes: `type`, `start`, `reversed`
  - Inline style `list-style-type` values: `lower-alpha`, `upper-alpha`, `lower-latin`, `upper-latin`, `circle`, `decimal`, `disc`, `lower-roman`, `upper-roman`, `square`
- TABLE/CAPTION/THEAD/TBODY/TFOOT/TR/TD/TH with support for:
  - TABLE attributes `border`, `cellpadding`, `cellspacing`
  - TD/TH attributes `colspan`, `rowspan`, `valign`
  - Table is scrollable if columns are too wide
- ABBR, ACRONYM, ADDRESS, ARTICLE, ASIDE, B, BIG, BLOCKQUOTE, BR, CENTER, CITE, CODE,
  DD, DEL, DETAILS, DFN, DIV, DL, DT, EM, FIGCAPTION, FIGURE, FONT, FOOTER, HEADER, HR, I, INS,
  KBD, MAIN, MARK, NAV, NOSCRIPT, P, PRE, Q, RP, RT, RUBY, S, SAMP, SECTION, SMALL,
  STRIKE, STRONG, STYLE, SUB, SUMMARY, SUP, TT, U, VAR
- Everything with screenshot: https://demo.fwfh.dev/supported/tags.html
- [Try with fwfh.dev](https://try.fwfh.dev)

These tags requires [flutter_widget_from_html](https://pub.dev/packages/flutter_widget_from_html):

- AUDIO
- IFRAME
- SVG
- VIDEO

These tags and their contents will be ignored:

- SCRIPT
- STYLE

### Attributes

- align: center/end/justify/left/right/start/-moz-center/-webkit-center
- dir: auto/ltr/rtl

### Inline stylings

- background: 1 value (color)
  - background-color: `currentcolor`, hex values, `rgb()`, `hsl()` or named colors
  - background-image: `url()` with support for asset (`asset://`), data uri, local file (`file://`) and network image
  - background-repeat: no-repeat/repeat/repeat-x/repeat-y
  - background-position: single or double instances of bottom/center/left/right/top (e.g. `top left`)
  - background-size: auto/contain/cover
- border: 3 values (width style color), 2 values (width style) or 1 value (width)
  - border-top, border-right, border-bottom, border-left
  - border-block-start, border-block-end
  - border-inline-start, border-inline-end
- border-radius: 4, 3, 2 or 1 value with slash support (e.g. `10px / 20px`)
  - border-top-left-radius: 2 values or 1 value in `em`, `pt` and `px`
  - border-top-right-radius: 2 values or 1 value in `em`, `pt` and `px`
  - border-bottom-right-radius: 2 values or 1 value in `em`, `pt` and `px`
  - border-bottom-left-radius: 2 values or 1 value in `em`, `pt` and `px`
- color: hex values, `rgb()`, `hsl()` or named colors
- direction (similar to `dir` attribute)
- display: block/flex/inline/inline-block/none
  - In `flex` mode:
    - flex-direction: column/row
    - align-items: flex-start/flex-end/center/baseline/stretch
    - gap: value in `em`, `%`, `pt` and `px`
    - justify-content: flex-start/flex-end/center/space-between/space-around/space-evenly
- font-family
- font-size: absolute (e.g. `xx-large`), relative (`larger`, `smaller`) or values in `em`, `%`, `pt` and `px`
- font-style: italic/normal
- font-weight: bold/normal/100..900
- line-height: `normal`, number or value in `em`, `%`, `pt` and `px`
- margin: 4 values, 2 values or 1 value in `em`, `pt` and `px`
  - margin-top, margin-right, margin-bottom, margin-left
  - margin-block-start, margin-block-end
  - margin-inline-start, margin-inline-end
- padding: 4 values, 2 values or 1 value in `em`, `pt` and `px`
  - padding-top, padding-right, padding-bottom, padding-left
  - padding-block-start, padding-block-end
  - padding-inline-start, padding-inline-end
- vertical-align: baseline/top/bottom/middle/sub/super
- text-align (similar to `align` attribute)
- text-decoration
  - text-decoration-color: `currentcolor`, hex values, `rgb()`, `hsl()` or named colors
  - text-decoration-line: line-through/none/overline/underline
  - text-decoration-style: dotted/dashed/double/solid
  - text-decoration-thickness, text-decoration-width: values in `%` only
- text-overflow: clip/ellipsis. Note: `text-overflow: ellipsis` should be used in conjuntion with `max-lines` or `-webkit-line-clamp` for better result.
- text-shadow:
  - 4 values (color x y blur, color x y blur)
  - 3 values (x y color, color x y)
  - Or 2 values (x y)
  - Multiple shadows
- white-space: pre/normal/nowrap
- Sizing: `auto` or values in `em`, `%`, `pt` and `px`
  - width, max-width, min-width
  - height, max-height, min-height

## Extensibility

This package implements widget building logic with high testing coverage to ensure correctness. It tries to render an optimal tree by using `RichText` with specific `TextStyle`, collapsing margins, proper whitespace handling, etc. The idea is to build a solid foundation for apps to customize.

The [enhanced](https://pub.dev/packages/flutter_widget_from_html) package uses a custom `WidgetFactory` with pre-built mixins for easy usage:

- [fwfh_cached_network_image](https://pub.dev/packages/fwfh_cached_network_image) for optimized image rendering
- [fwfh_chewie](https://pub.dev/packages/fwfh_chewie) for VIDEO support
- [fwfh_just_audio](https://pub.dev/packages/fwfh_just_audio) for AUDIO support
- [fwfh_svg](https://pub.dev/packages/fwfh_svg) for SVG support
- [fwfh_url_launcher](https://pub.dev/packages/fwfh_url_launcher) to launch URLs
- [fwfh_webview](https://pub.dev/packages/fwfh_webview) for IFRAME support

See [the extensibility document](https://github.com/daohoangson/flutter_widget_from_html/blob/v0.14.2/docs/extensibility.md) for detailed information.

<a href="https://www.buymeacoffee.com/daohoangson" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>
