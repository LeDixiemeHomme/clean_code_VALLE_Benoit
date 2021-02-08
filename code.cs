using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Reflection.Metadata;
namespace Sandbox
{
    class Program
    {
        static void Main(string[] args)
        {
            var app = new App(new DefaultFileSystem(), new ConsoleOutput());
            //app.Execute("db.txt");
            app.Execute2( "db.txt", (index, number) =>
                {
                    var bigNumber = number.ToUpper();
                    Console.WriteLine($"{index} >> {bigNumber}");
                });
            app.Execute2("db.txt", new DefaultActionWithNumber().DoAction);
            app.Execute3("db.txt", new DefaultActionWithNumber());

            //var calculator = new Calculator();
            //calculator.RegisterOperation("add",new AddOperation());
            //calculator.RegisterOperation("prod",new ProdOperation());
            //var result = calculator.Execute("prod", 3, 2);
            //Console.WriteLine(result);
        }
        //law of Demeter:
        //une classe ne peut utiliser que:
        //- ses membres privées
        //- elle meme
        //- parametres passés a la fonction
        //- objets externes passés au constructeur
        //
        //don't:
        // pas de variables globales / statiques du systeme
        // pas de new bidule()
    }
    public class App
    {
        private IFileSystem _fileSystem;
        private readonly IOutput _output;
        public App(IFileSystem fileSystem, IOutput output)
        {
            _fileSystem = fileSystem;
            _output = output;
        }
        public IEnumerable<string> ReadExtractNumbers(string path)
        {
            var content = _fileSystem.ReadAllText(path);
            var numbers = content.Split(",");
            return numbers;
        }

        public void Execute3(string dbFilePath, IActionWithNumber actionWithNumber)
        {
            int i = 1;
            foreach (var number in ReadExtractNumbers(dbFilePath))
            {
                actionWithNumber.DoAction(i,number);
                i++;
            }
        }
    }
    public interface IActionWithNumber
    {
        void DoAction(int index, string number);
    }

    public class DefaultActionWithNumber : IActionWithNumber
    {
        public void DoAction(int index, string number)
        {
            var bigNumber = number.ToUpper();
            Console.WriteLine($"{index} >> {bigNumber}");
        }
    }
    public interface IOutput
    {
        void WriteLine(string content);
    }
    public class ConsoleOutput : IOutput
    {
        public void WriteLine(string content)
            => Console.WriteLine(content);
    }
    public interface IFileSystem
    {
        string ReadAllText(string path);
    }

    public class DefaultFileSystem : IFileSystem
    {
        public string ReadAllText(string path)
            => File.ReadAllText(path);
    }
}
