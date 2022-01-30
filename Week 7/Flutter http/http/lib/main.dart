import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';
import 'dart:developer';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  late String item;

 

  @override
  Widget build(BuildContext context) {
    
    return MaterialApp(
      title: 'Welcome to Flutter',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Welcome to Flutter'),
        ),
        body: Center(
        child: Builder(
            builder: (BuildContext context) {
              return Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [const Text('Enter a currency item below'),
                            TextField(
                            onChanged: (text) {item = text;},
                            decoration: const InputDecoration(
                            border: OutlineInputBorder(),
                            hintText: 'Enter a search term',
                            ),
                           ),
                           ElevatedButton(
                            child: const Text('Move to page 2'),
                            onPressed: () {
                              Navigator.push(
                              context,
                              MaterialPageRoute(builder: (context) => const SecondRoute(),
                              settings: RouteSettings(arguments: item,),
                              ),
                            );
                            },
                           ),
                           ],
              );
            },
          ),
        ),
      ),
    );
  }
}

Future<Currency> fetchCurrency(String name) async {
 //print(name);
  final response = await http
      .get(Uri.parse('https://poe.ninja/api/data/CurrencyOverview?league=Standard&type=Currency'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    Map mapRes = json.decode(response.body);
    //print('Response from server: $mapRes');
    for(int i = 0; i<mapRes['lines'].length;i++){
      print(mapRes['lines'][i]['currencyTypeName']);
      if(mapRes['lines'][i]['currencyTypeName'] == name){
        return Currency(
          name: mapRes['lines'][i]['currencyTypeName'],
          price: mapRes['lines'][i]['receive']['value'].toString(),
    );
      }
    }


    throw Exception('Could not find $name in list');
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Could not find $name in list');
  }
}

class Currency {
  final String name;
  final String price;
  //final String imgURL;
  //Image.network() for displaying image from url
  const Currency({
    required this.name,
    required this.price,
   // required this.imgURL,
  });
/*
  factory Currency.fromJson(Map<String, dynamic> json) {
    return Currency(
      name: json['currencyTypeName'],
      price: json['value'],
      //imgURL: json['icon'],
    );
  }
  */
}

class SecondRoute extends StatefulWidget {
  const SecondRoute({Key? key}) : super(key: key);

  @override
  State<SecondRoute> createState() => _SecondRouteState();
}

class _SecondRouteState extends State<SecondRoute> {
  late Future<Currency> futureCurrency;
  
  @override
    void initState() {
      super.initState();
      
    }

  @override
  Widget build(BuildContext context) {
    final name = ModalRoute.of(context)!.settings.arguments;
    futureCurrency = fetchCurrency(name.toString());
    return MaterialApp(
      title: 'Page 2',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Page 2'),
        ),
        body: Center(
        child: Builder(
            builder: (BuildContext context) {
              return Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                           FutureBuilder<Currency>(
                            future: futureCurrency,
                            builder: (context, snapshot) {
                              if (snapshot.hasData) {
                                return Text('Name: ' + snapshot.data!.name + 
                                ' Price: ' + snapshot.data!.price);
                              } else if (snapshot.hasError) {
                                return Text('${snapshot.error}');
                              }
                              // By default, show a loading spinner.
                              return const CircularProgressIndicator();
                           },
                           ),
                           ElevatedButton(
                              onPressed: () {
                                Navigator.pop(context);
                              },
                              child: const Text('Go back to page 1'),
                            ),
                           ],
              );
            },
          ),
        ),
      ),
    );
  }
}