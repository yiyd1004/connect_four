import 'dart:convert';
import 'dart:io';

import 'package:connect_four/connect_four.dart' as connect_four;

/// Connect Four
/// First player's name:
/// > Ava
/// Second player's name:
/// > Oliver
/// Set the board dimensions (Rows x Columns)
/// Press Enter for default (6 x 7)
/// >
/// Ava VS Oliver
/// 6 X 7 board

  String? firstPlayer;
  String? secondPlayer;

  void main(List<String> arguments) {
    print("Connect Four");

    _getPlayerName();
    _getBoardSize();
  }

  void _getPlayerName() {
    print("First player's name:");
    firstPlayer = stdin.readLineSync(encoding: SystemEncoding());

    print("Second player's name:");
    secondPlayer = stdin.readLineSync(encoding: SystemEncoding());
  }

  void _getBoardSize() {
    RegExp reg = RegExp(
        r"(.*)\s*(\d)+\s*[xX]\s*(\d)+\s*(.*)",
        multiLine: true,
        caseSensitive: false);

    while (true) {
      print("Set the board dimensions (Rows x Columns)");
      print("Press Enter for default (6 x 7)");

      String? boardSize = stdin.readLineSync(encoding: SystemEncoding());

      if (boardSize == null || boardSize.isEmpty) {
        print("$firstPlayer VS $secondPlayer\n6 X 7 board");
        break;
      } else {
        var match = reg.firstMatch(boardSize);

        if (match != null && match.groupCount == 4) {
          int? row = int.tryParse(match.group(2).toString());
          int? col = int.tryParse(match.group(3).toString());

          if (row !=  null && col != null) {
            int isBoardValidate = _validateBoardSize(row, col);

            if (isBoardValidate == 0) {
              print("$firstPlayer VS $secondPlayer\n$row X $col board");
              break;
            } else if (isBoardValidate == 1){
              print("Board rows should be from 5 to 9");
            } else {
              print("Board columns should be from 5 to 9");
            }
          } else {
            print("Invalid input");
          }
        } else {
          print("Invalid input");
        }
      }
    }
  }

  int _validateBoardSize(int row, int col) {
    if (row < 5 || row > 9) {
      return 1;
    }

    if (col < 5 || col > 9) {
      return 2;
    }

    return 0;
  }