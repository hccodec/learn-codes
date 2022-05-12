import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: '测试程序',
      home: new RandomWords(),
      // body: new Center(child: new RandomWords()),
    );
  }
}

class RandomWordsState extends State<RandomWords> {
  final _suggestions = <WordPair>[];

  final _saved = new Set<WordPair>();

  final _biggerFont = const TextStyle(fontSize: 20.0);

  void _pushSaved() {
    Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
      final tiles = _saved.map((pair) {
        return new ListTile(
            title: new Text(pair.asPascalCase, style: _biggerFont));
      });
      final divided =
          ListTile.divideTiles(tiles: tiles, context: context).toList();

      return new Scaffold(
        appBar: new AppBar(title: new Text('Saved Suggestions')),
        body: new ListView(children: divided),
      );
    }));
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text('Word Pair TEST'), actions: <Widget>[
        new IconButton(icon: new Icon(Icons.list), onPressed: _pushSaved)
      ]),
      body: _buildSuggestions(),
      // body: new Center(child: new RandomWords()),
    );
  }

  Widget _buildSuggestions() {
    return new ListView.builder(itemBuilder: (context, i) {
      if (i.isOdd) return new Divider();
      final index = i ~/ 2;
      if (index >= _suggestions.length)
        _suggestions.addAll(generateWordPairs().take(10));
      return _buildRow(_suggestions[index], index);
    });
  }

  Widget _buildRow(WordPair pair, int index) {
    final alreadySaved = _saved.contains(pair);

    return new ListTile(
      title: new Text((index + 1).toString() + ". " + pair.asPascalCase,
          style: _biggerFont),
      trailing: new Icon(alreadySaved ? Icons.favorite : Icons.favorite_border,
          color: alreadySaved ? Colors.red : null),
      onTap: () {
        setState(() {
          alreadySaved ? _saved.remove(pair) : _saved.add(pair);
        });
      },
    );
  }
}

class RandomWords extends StatefulWidget {
  @override
  createState() => new RandomWordsState();
}
